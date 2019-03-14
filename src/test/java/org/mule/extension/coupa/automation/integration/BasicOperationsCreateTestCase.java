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
import org.mule.runtime.api.metadata.DataType;
import org.mule.runtime.api.metadata.TypedValue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class BasicOperationsCreateTestCase extends AbstractTestCase {

    @Test
    public void createAddressTest() throws Exception {

        String json = "{\"name\": \"testing\", " +
                "\"street1\":\"" + timestamp + " foo ave.\", " +
                "\"city\": \"New York\"," +
                "\"postal_code\": \"10003\"," +
                "\"country\":{\"code\": \"US\"} }";

        TypedValue<String> payload = flowRunner("create-object")
                .withVariable("objectType", Constants.ADDRESS_KEY)
                .withPayload(json)
                .run()
                .getMessage()
                .getPayload();

        BindingContext bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload, DataType.OBJECT))
                .build();
        Map<String, Object> value =
                (Map<String, Object>) expressionManager.evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext).getValue();
        assertTrue(value.containsKey("id"));
    }

    @Test
    public void createExpenseReportTest() throws Exception {

        String json = "{\"title\": \"new test expense report\"}";

        TypedValue<String> payload = flowRunner("create-object")
                .withVariable("objectType", Constants.EXPENSE_REPORT_KEY)
                .withPayload(json)
                .run()
                .getMessage()
                .getPayload();

        BindingContext bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload, DataType.OBJECT))
                .build();
        Map<String, Object> value =
                (Map<String, Object>) expressionManager.evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext).getValue();
        assertTrue(value.containsKey("id"));
    }

    @Test
    public void createInventoryTransaction() throws Exception {
        String json = "{\"price\": \"21.00\", " +
                "\"quantity\": \"1.0\"," +
                "\"total\": \"21.00\"," +
                "\"type\": \"ReceivingQuantityConsumption\"," +
                "\"order-line\": { \"id\": 8159}," +
                "\"uom\": { \"id\": 1 } } ";

        TypedValue<String> payload = flowRunner("create-object")
                .withVariable("objectType", Constants.INVENTORY_TRANSACTION_KEY)
                .withPayload(json)
                .run()
                .getMessage()
                .getPayload();

        BindingContext bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload, DataType.OBJECT))
                .build();
        Map<String, Object> value =
                (Map<String, Object>) expressionManager.evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext).getValue();
        assertTrue(value.containsKey("id"));
    }

    @Test
    public void createLookupValueTest() throws Exception {

        final String json = "{" +
                "\"name\": \"" + LocalDateTime.now() + "\"," +
                "\"external-ref-code\": \"45004\"," +
                "\"lookup-id\": 2" +
                "}";

        TypedValue<String> payload = flowRunner("create-object")
                .withVariable("objectType", Constants.LOOKUP_VALUE_KEY)
                .withPayload(json)
                .run()
                .getMessage()
                .getPayload();

        BindingContext bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload, DataType.OBJECT))
                .build();
        Map<String, Object> value =
                (Map<String, Object>) expressionManager.evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext).getValue();
        assertTrue(value.containsKey("id"));
    }

    @Test
    public void createPurchaseOrderTest() throws Exception {

        final String json = "{" +
                "  \"type\": \"ExternalOrderHeader\"," +
                "  \"status\": \"draft\"," +
                "  \"payment-method\": \"invoice\"," +
                "  \"po-number\": " + DateTimeFormatter.ofPattern("yyyyMMddhhmmss").format(LocalDateTime.now()) +"," +
                "  \"created-by\": {" +
                "    \"id\": 437" +
                "  }," +
                "  \"requisition-header\": {" +
                "    \"id\": 5439" +
                "  }," +
                "  \"ship-to-address\": {" +
                "    \"id\": 160" +
                "  }," +
                "  \"ship-to-user\": {" +
                "    \"id\": 30" +
                "  }," +
                "  \"supplier\": {" +
                "    \"id\": 392" +
                "  }," +
                "  \"payment-term\": {" +
                "    \"id\": 5" +
                "  }," +
                "  \"shipping-term\": {" +
                "    \"id\": 7" +
                "  }," +
                "  \"attachments\": []" +
                "}";

        TypedValue<String> payload = flowRunner("create-object")
                .withVariable("objectType", Constants.PURCHASE_ORDER_KEY)
                .withPayload(json)
                .run()
                .getMessage()
                .getPayload();

        BindingContext bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload, DataType.OBJECT))
                .build();
        Map<String, Object> value =
                (Map<String, Object>) expressionManager.evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext).getValue();
        assertTrue(value.containsKey("id"));
    }

    @Test
    public void createRequisitionTest() throws Exception {

        final String json = "{" +
                "   \"requested-by\":{" +
                "      \"id\":30" +
                "   }," +
                "   \"requisition-lines\":[" +
                "      {" +
                "         \"id\":5457," +
                "         \"description\":\"Training Request\"," +
                "         \"line-num\":2," +
                "         \"order-line-id\":5640," +
                "         \"total\":\"200.00\"," +
                "         \"line-type\":\"RequisitionAmountLine\"," +
                "         \"unit-price\":\"200.00\"" +
                "      }" +
                "   ]" +
                "}";

        TypedValue<String> payload = flowRunner("create-object")
                .withVariable("objectType", Constants.REQUISITION_KEY)
                .withPayload(json)
                .run()
                .getMessage()
                .getPayload();

        BindingContext bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload, DataType.OBJECT))
                .build();
        Map<String, Object> value =
                (Map<String, Object>) expressionManager.evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext).getValue();
        assertTrue(value.containsKey("id"));
    }

    @Test
    public void createSupplierTest() throws Exception {

        final String json = "{" +
                "    \"name\": \"" + timestamp + "\"," +
                "    \"display-name\": \"Patrick car shop\"," +
                "    \"status\": \"active\"," +
                "    \"website\": \"\"," +
                "    \"po-method\": \"prompt\"," +
                "    \"po-change-method\": \"prompt\"," +
                "    \"payment-method\": \"invoice\"," +
                "    \"cxml-url\": \"\"," +
                "    \"cxml-domain\": \"\"," +
                "    \"cxml-identity\": \"\"," +
                "    \"cxml-supplier-domain\": \"\"," +
                "    \"cxml-supplier-identity\": \"\"," +
                "    \"cxml-secret\": null," +
                "    \"cxml-protocol\": \"\"," +
                "    \"cxml-http-username\": null," +
                "    \"cxml-ssl-version\": null," +
                "    \"disable-cert-verify\": false," +
                "    \"po-email\": \"\"," +
                "    \"account-number\": \"\"," +
                "    \"duns\": \"\"," +
                "    \"tax-id\": \"\"," +
                "    \"coupa-connect-secret\": null," +
                "    \"buyer-hold\": false," +
                "    \"invoice-matching-level\": \"3-way\"," +
                "    \"allow-cxml-invoicing\": false," +
                "    \"cxml-invoice-supplier-domain\": \"\"," +
                "    \"cxml-invoice-supplier-identity\": \"\"," +
                "    \"cxml-invoice-buyer-domain\": \"\"," +
                "    \"cxml-invoice-buyer-identity\": \"\"," +
                "    \"cxml-invoice-secret\": \"\"," +
                "    \"on-hold\": false," +
                "    \"savings-pct\": \"8.0\"," +
                "    \"allow-inv-from-connect\": true," +
                "    \"allow-inv-no-backing-doc-from-connect\": false," +
                "    \"allow-inv-unbacked-lines-from-connect\": false," +
                "    \"allow-inv-choose-billing-account\": null," +
                "    \"allow-csp-access-without-two-factor\": null," +
                "    \"primary-contact\": {" +
                "        \"id\": 82," +
                "        \"created-at\": \"2012-12-12T10:36:26-08:00\"," +
                "        \"updated-at\": \"2012-12-12T10:36:26-08:00\"," +
                "        \"email\": \"\"," +
                "        \"name-prefix\": null," +
                "        \"name-suffix\": null," +
                "        \"name-additional\": null," +
                "        \"name-given\": \"\"," +
                "        \"name-family\": \"\"," +
                "        \"name-fullname\": null," +
                "        \"notes\": null" +
                "    }," +
                "    \"primary-address\": {" +
                "        \"id\": 95," +
                "        \"created-at\": \"2012-12-12T10:36:26-08:00\"," +
                "        \"updated-at\": \"2012-12-12T10:36:26-08:00\"," +
                "        \"name\": null," +
                "        \"location-code\": null," +
                "        \"street1\": \"\"," +
                "        \"street2\": \"\"," +
                "        \"city\": \"\"," +
                "        \"state\": \"\"," +
                "        \"postal-code\": \"\"," +
                "        \"attention\": null," +
                "        \"active\": true," +
                "        \"business-group-name\": null," +
                "        \"vat-number\": null," +
                "        \"local-tax-number\": null," +
                "        \"address-direct\": \"\"," +
                "        \"content-groups\": []" +
                "    }," +
                "    \"remit-to-addresses\": []," +
                "    \"send-invoices-to-approvals\": false," +
                "    \"content-groups\": [" +
                "        {" +
                "            \"id\": 28," +
                "            \"created-at\": \"2016-07-14T09:24:59-07:00\"," +
                "            \"updated-at\": \"2016-09-22T23:55:00-07:00\"," +
                "            \"name\": \"English - USD\"," +
                "            \"description\": \"\"" +
                "        }" +
                "    ]" +
                "}";

        TypedValue<String> payload = flowRunner("create-object")
                .withVariable("objectType", Constants.SUPPLIER_KEY)
                .withPayload(json)
                .run()
                .getMessage()
                .getPayload();

        BindingContext bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload, DataType.OBJECT))
                .build();
        Map<String, Object> value =
                (Map<String, Object>) expressionManager.evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext).getValue();
        assertTrue(value.containsKey("id"));
    }

    @Test
    public void createUserTest() throws Exception {
        final String time = DateTimeFormatter.ofPattern("yyyyMMddhhmmss").format(LocalDateTime.now());
        final String json = "{" +
                "    \"firstname\": \"Blair (AP Clerk)\"," +
                "    \"lastname\": \"Hostler\"," +
                "    \"login\": \"" + time + "\"," +
                "    \"email\": \""+ time + "@coupa.com\"" +
                "}";

        TypedValue<String> payload = flowRunner("create-object")
                .withVariable("objectType", Constants.USER_KEY)
                .withPayload(json)
                .run()
                .getMessage()
                .getPayload();

        BindingContext bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload, DataType.OBJECT))
                .build();
        Map<String, Object> value =
                (Map<String, Object>) expressionManager.evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext).getValue();
        assertTrue(value.containsKey("id"));
    }
}
