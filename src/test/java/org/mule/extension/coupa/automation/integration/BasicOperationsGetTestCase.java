/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.automation.integration;

import org.junit.Test;
import org.mule.extension.coupa.internal.utils.Constants;
import org.mule.runtime.api.el.BindingContext;
import org.mule.runtime.api.event.Event;
import org.mule.runtime.api.metadata.DataType;
import org.mule.runtime.api.metadata.TypedValue;
import org.mule.runtime.api.streaming.object.CursorIterator;
import org.mule.runtime.api.streaming.object.CursorIteratorProvider;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BasicOperationsGetTestCase extends AbstractTestCase {

    @Test
    public void getAddressTest() throws Exception {

        final String ID = "172";

        TypedValue<String> payload = flowRunner("get-object")
                                        .withVariable("objectType", Constants.ADDRESS_KEY)
                                        .withPayload(ID)
                                        .run()
                                        .getMessage()
                                        .getPayload();

        BindingContext bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload, DataType.OBJECT))
                .build();
        Map<String, Object> value =
                (Map<String, Object>) expressionManager
                        .evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext)
                        .getValue();
        assertThat(value, hasEntry("id", Integer.parseInt(ID)));
    }

    @Test
    public void getApprovalTest() throws Exception {

        final String ID = "12622";

        TypedValue<String> payload = flowRunner("get-object")
                .withVariable("objectType", Constants.APPROVAL_KEY)
                .withPayload(ID)
                .run()
                .getMessage()
                .getPayload();

        BindingContext bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload, DataType.OBJECT))
                .build();
        Map<String, Object> value =
                (Map<String, Object>) expressionManager.evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext).getValue() ;
        assertThat(value, hasEntry("id", Integer.parseInt(ID))); }

    @Test
    public void getExpenseReportTest() throws Exception {

        final String ID = "1";

        TypedValue<String> payload = flowRunner("get-object")
                .withVariable("objectType", Constants.EXPENSE_REPORT_KEY)
                .withPayload(ID)
                .run()
                .getMessage()
                .getPayload();

        BindingContext bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload, DataType.OBJECT))
                .build();
        Map<String, Object> value =
                (Map<String, Object>) expressionManager.evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext).getValue() ;
        assertThat(value, hasEntry("id", Integer.parseInt(ID)));
    }

    @Test
    public void getInventoryTransaction() throws Exception {

        final String ID = "1";

        TypedValue<String> payload = flowRunner("get-object")
                .withVariable("objectType", Constants.INVENTORY_TRANSACTION_KEY)
                .withPayload(ID)
                .run()
                .getMessage()
                .getPayload();

        BindingContext bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload, DataType.OBJECT))
                .build();
        Map<String, Object> value =
                (Map<String, Object>) expressionManager.evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext).getValue() ;
        assertThat(value, hasEntry("id", Integer.parseInt(ID)));
    }

    @Test
    public void getInvoiceTest() throws Exception {

        final String ID = "3426";

        TypedValue<String> payload = flowRunner("get-object")
                .withPayload(ID)
                .withVariable("objectType", Constants.INVOICE_KEY)
                .run()
                .getMessage()
                .getPayload();

        BindingContext bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload, DataType.OBJECT))
                .build();
        Map<String, Object> value =
                (Map<String, Object>) expressionManager.evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext).getValue() ;

        assertThat(value, hasEntry("id", Integer.parseInt(ID)));
    }

    @Test
    public void getLookupValueTest() throws Exception {

        final String ID = "1";

        Event event = flowRunner("get-object")
                .withPayload(ID)
                .withVariable("objectType", Constants.LOOKUP_VALUE_KEY)
                .run();
        TypedValue<String> payload = event.getMessage().getPayload();

        BindingContext bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload, DataType.OBJECT))
                .build();
        Map<String, Object> value =
                (Map<String, Object>) expressionManager.evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext).getValue() ;

        assertThat(value, hasEntry("id", Integer.parseInt(ID)));
    }

    @Test
    public void getPurchaseOrderTest() throws Exception {
        final String ID = "4";

        TypedValue<String> payload  = flowRunner("get-object")
                .withPayload(ID)
                .withVariable("objectType", Constants.PURCHASE_ORDER_KEY)
                .run()
                .getMessage()
                .getPayload();

        BindingContext bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload, DataType.OBJECT))
                .build();
        Map<String, Object> value =
                (Map<String, Object>) expressionManager.evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext).getValue() ;

        assertThat(value, hasEntry("id", Integer.parseInt(ID)));
    }

    @Test
    public void getSupplierTest() throws Exception {

        final String ID = "1";

        TypedValue<String> payload = flowRunner("get-object")
                .withPayload(ID)
                .withVariable("objectType", Constants.SUPPLIER_KEY)
                .run()
                .getMessage()
                .getPayload();

        BindingContext bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload, DataType.OBJECT))
                .build();
        Map<String, Object> value =
                (Map<String, Object>) expressionManager.evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext).getValue() ;

        assertThat(value, hasEntry("id", Integer.parseInt(ID)));
    }

    @Test
    public void getSupplierSubPathTest() throws Exception {

        final String ID = "1";

        TypedValue<String> payload = flowRunner("get-supplier-subpath")
                .withVariable("id", ID)
                .keepStreamsOpen()
                .run()
                .getMessage()
                .getPayload();

        BindingContext bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload, DataType.OBJECT))
                .build();
        CursorIteratorProvider results =
                (CursorIteratorProvider)expressionManager
                        .evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext).getValue() ;

        assertNotNull(results);
        int count = 0;
        CursorIterator it = results.openCursor();
        while(it.hasNext()) {
            count++;
            it.next();
        }
        assertTrue(count > 0);
        results.close();
    }

    @Test
    public void getUserTest() throws Exception {

        final String ID = "6";

        TypedValue<String> payload = flowRunner("get-object")
                .withPayload(ID)
                .withVariable("objectType", Constants.USER_KEY)
                .run()
                .getMessage()
                .getPayload();

        BindingContext bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload, DataType.OBJECT))
                .build();
        Map<String, Object> value =
                (Map<String, Object>) expressionManager.evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext).getValue() ;

        assertThat(value, hasEntry("id", Integer.parseInt(ID)));
    }








}
