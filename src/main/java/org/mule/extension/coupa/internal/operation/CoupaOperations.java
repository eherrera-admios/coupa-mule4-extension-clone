/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.internal.operation;

import org.mule.extension.coupa.internal.CoupaError;
import org.mule.extension.coupa.internal.connection.CoupaConnection;
import org.mule.extension.coupa.internal.connection.api.BasicApi;
import org.mule.extension.coupa.internal.error.CoupaException;
import org.mule.runtime.api.el.BindingContext;
import org.mule.runtime.api.metadata.MediaType;
import org.mule.runtime.api.metadata.TypedValue;
import org.mule.runtime.core.api.el.ExpressionManager;
import org.mule.runtime.extension.api.annotation.Ignore;
import org.mule.runtime.extension.api.runtime.operation.Result;
import org.mule.runtime.extension.api.runtime.process.CompletionCallback;
import org.mule.runtime.extension.api.runtime.streaming.PagingProvider;
import org.mule.runtime.http.api.HttpConstants;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;

import static java.lang.String.format;
import static org.mule.extension.coupa.internal.utils.Constants.*;
import static org.mule.extension.coupa.internal.utils.CoupaUtils.*;
import static org.mule.runtime.api.metadata.MediaType.APPLICATION_JSON;

public class CoupaOperations {

    private final static String RESULT_EXPRESSION = "#[output application/java --- {\n" +
            "    \"original\" : write(payload, \"application/json\"),\n" +
            "    \"errors\" : payload.errors\n" +
            "} as Object]";

    private final static Logger logger = LoggerFactory.getLogger(CoupaOperations.class);

    private final static String CREATE_ENTITY_EXPRESSION = "#[output application/json --- payload]";


    @Inject
    ExpressionManager expressionManager;

    private void processHttpResponse(CompletionCallback callback, BindingContext bindingContext, CoupaError defaultError, boolean isStreamExpected) {

        if (isStreamExpected) {

            TypedValue payload = expressionManager.evaluate("#[payload]", bindingContext);
            Result.Builder resultBuilder = Result.builder()
                    .output(payload.getValue())
                    .mediaType(MediaType.create("image", "png"));
            callback.success(resultBuilder.build());

        } else {

            TypedValue<?> resultWrapper = expressionManager.evaluate(RESULT_EXPRESSION, bindingContext);
            Map<String, Object> value = (HashMap<String, Object>) resultWrapper.getValue();

            if (value.get("errors") != null) {
                throw new CoupaException(
                        format("An error occurred calling the Coupa API.\n Errors: \n %s \n",
                                value.get("errors")), defaultError);
            }

            if (value.get("original") == null) {
                throw new CoupaException(
                        format("An error occurred calling the Coupa API.\n Errors: \n %s \n",
                                "Empty Response"), defaultError);
            }

            Object payload = expressionManager.evaluate("#[payload]", getBindingContext(value.get("original"))).getValue();

            Result.Builder resultBuilder = Result.builder()
                    .output(payload)
                    .mediaType(APPLICATION_JSON);

            callback.success(resultBuilder.build());
        }

    }

    public class CoupaResponseConsumer<P, A> implements BiConsumer<HttpResponse, Throwable> {

        private final CoupaError coupaError;
        private final CompletionCallback<P, A> callback;
        private final boolean isStreamExpected;

        public CoupaResponseConsumer(CoupaError coupaError, CompletionCallback<P, A> callback) {
            this.coupaError = coupaError;
            this.callback = callback;
            this.isStreamExpected = false;
        }

        public CoupaResponseConsumer(CoupaError coupaError, CompletionCallback<P, A> callback, boolean isStreamExpected) {
            this.coupaError = coupaError;
            this.callback = callback;
            this.isStreamExpected = isStreamExpected;
        }

        @Override
        public void accept(HttpResponse httpResponse, Throwable throwable) {
            try {

                if (httpResponse.getStatusCode() == HttpConstants.HttpStatus.UNAUTHORIZED.getStatusCode()) {
                    logger.error("Unauthorized access");
                    throw new CoupaException("Unauthorized access", CoupaError.INVALID_AUTH);
                }

                if (httpResponse.getStatusCode() == HttpConstants.HttpStatus.INTERNAL_SERVER_ERROR.getStatusCode()) {
                    logger.error("Unknown error");
                    throw new CoupaException("Unauthorized access", CoupaError.UNKNOWN);
                }

                if (isStreamExpected) {
                    processHttpResponse(callback, getBindingContextStream(httpResponse.getEntity().getContent()), coupaError, true);
                } else {
                    processHttpResponse(callback, getBindingContext(httpResponse.getEntity().getContent()), coupaError, false);
                }

            } catch (Exception e) {
                logger.error("Unknown error", e);
                callback.error(e);
            }
        }
    }

    @Ignore
    protected PagingProvider<CoupaConnection, Map<String, Object>> getPagingProvider(String objectType, String id, QueryParams queryParams) {

        return new PagingProvider<CoupaConnection, Map<String, Object>>() {

            final AtomicLong atomicInteger = new AtomicLong(0);

            @Override
            public List<Map<String, Object>> getPage(CoupaConnection coupaConnection) {

                List<Map<String, Object>> result = new ArrayList<>();

                try {
                    HttpResponse httpResponse = getApi(objectType, coupaConnection)
                            .querySync(id, queryParams.getQuery(), queryParams.getParams(), atomicInteger.getAndAdd(50));

                    if (httpResponse.getStatusCode() == HttpConstants.HttpStatus.OK.getStatusCode()) {

                        BindingContext bindingContext = getBindingContext(httpResponse.getEntity().getContent());
                        TypedValue payload = expressionManager.evaluate("#[output application/java --- payload]", bindingContext);
                        List<Map<String, Object>> resultList = (ArrayList<Map<String, Object>>) payload.getValue();

                        result.addAll(resultList);

                    }
                } catch (Exception e) {
                    logger.error("Coupa API Error. {}\n", e.getMessage(), e);
                }

                return result;

            }

            @Override
            public java.util.Optional<Integer> getTotalResults(CoupaConnection connection) {
                return java.util.Optional.empty();
            }

            @Override
            public void close(CoupaConnection connection) {

            }

        };
    }

    @Ignore
    protected BasicApi getApi(String key, CoupaConnection connection) {

        switch (key) {
            case INVOICE_KEY:
            case INVOICE:
                return connection.getInvoices();
            case USER_KEY:
            case USER:
                return connection.getUsers();
            case SUPPLIER_KEY:
            case SUPPLIER:
                return connection.getSuppliers();
            case REQUISITION_KEY:
            case REQUISITION:
                return connection.getRequisitions();
            case PURCHASE_ORDER_KEY:
            case PURCHASE_ORDER:
                return connection.getPurchaseOrders();
            case LOOKUP_VALUE_KEY:
            case LOOKUP_VALUE:
                return connection.getLookupValues();
            case ADDRESS_KEY:
            case ADDRESS:
                return connection.getAddresses();
            case EXPENSE_REPORT_KEY:
            case EXPENSE_REPORT:
                return connection.getExpenseReports();
            case INVENTORY_TRANSACTION_KEY:
            case INVENTORY_TRANSACTION:
                return connection.getInventoryTransactions();
            case APPROVAL_KEY:
            case APPROVAL:
                return connection.getApprovals();

        }

        throw new CoupaException("Unknown Object/Action Key chosen", CoupaError.INVALID_PARAMS);

    }

}
