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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BasicOperationsQueryTestCase extends AbstractTestCase {

    @Test
    public void queryAddressTest() throws Exception {

        TypedValue<String> payload = flowRunner("query-object")
                .withVariable("objectType", Constants.ADDRESS_KEY)
                .withVariable("query", "?id=172")
                .keepStreamsOpen()
                .run()
                .getMessage()
                .getPayload();
        BindingContext bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload, DataType.OBJECT))
                .build();
        CursorIteratorProvider results =
                (CursorIteratorProvider)expressionManager
                        .evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext).getValue();
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
    public void queryApprovalTest() throws Exception {

        TypedValue<String> payload = flowRunner("query-object")
                .withVariable("objectType", Constants.APPROVAL_KEY)
                .withVariable("query", "?status=rejected")
                .keepStreamsOpen()
                .run()
                .getMessage()
                .getPayload();

        BindingContext bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload, DataType.OBJECT))
                .build();
        CursorIteratorProvider results = (CursorIteratorProvider)expressionManager.evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext).getValue() ;

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
    public void queryExpenseReportTest() throws Exception {

        TypedValue<String> payload = flowRunner("query-object")
                .withVariable("objectType", Constants.EXPENSE_REPORT_KEY)
                .withVariable("query", "?status=approved_for_payment&total[gt]=10000")
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
    public void queryInventoryTransaction() throws Exception {

        TypedValue<String> payload = flowRunner("query-object")
                .withVariable("objectType", Constants.INVENTORY_TRANSACTION_KEY)
                .withVariable("query", "?status=voided")
                .keepStreamsOpen()
                .run()
                .getMessage()
                .getPayload();

        BindingContext bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload, DataType.OBJECT))
                .build();
        CursorIteratorProvider results = (CursorIteratorProvider)expressionManager.evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext).getValue() ;

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
    public void queryInvoiceTest() throws Exception {

        Event event = flowRunner("query-object")
                .withVariable("objectType", Constants.INVOICE_KEY)
                .withVariable("query", "?id[lt]=60")
                .keepStreamsOpen()
                .run();
        TypedValue<String> payload = event.getMessage().getPayload();

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
    public void queryLookupValueTest() throws Exception {

        Event event = flowRunner("query-object")
                .withVariable("objectType", Constants.LOOKUP_VALUE_KEY)
                .withVariable("query", "?active=false")
                .keepStreamsOpen()
                .run();
        TypedValue<String> payload = event.getMessage().getPayload();

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
    public void queryPurchaseOrderTest() throws Exception {

        Event event = flowRunner("query-object")
                .withVariable("objectType", Constants.PURCHASE_ORDER_KEY)
                .withVariable("query", "?id[lt]=60")
                .keepStreamsOpen()
                .run();

        TypedValue<String> payload = event.getMessage().getPayload();

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
    public void queryRequisitionTest() throws Exception {

        Event event = flowRunner("query-object")
                .withVariable("objectType", Constants.REQUISITION_KEY)
                .withVariable("query", "?exported=true")
                .keepStreamsOpen()
                .run();
        TypedValue<String> payload = event.getMessage().getPayload();

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
    public void querySupplierTest() throws Exception {

        Event event = flowRunner("query-object")
                .withVariable("objectType", Constants.SUPPLIER_KEY)
                .withVariable("query", "?id=1")
                .keepStreamsOpen()
                .run();
        TypedValue<String> payload = event.getMessage().getPayload();

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
    public void querySupplierPathTest() throws Exception {

        Event event = flowRunner("query-object")
                .withVariable("objectType", Constants.SUPPLIER_KEY)
                .withVariable("query", "?id[lt]=60")
                .keepStreamsOpen()
                .run();
        TypedValue<String> payload = event.getMessage().getPayload();

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
    public void queryUserTest() throws Exception {

        Event event = flowRunner("query-object")
                .withVariable("objectType", Constants.USER_KEY)
                .withVariable("query", "?id[lt]=60")
                .keepStreamsOpen()
                .run();
        TypedValue<String> payload = event.getMessage().getPayload();

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
}
