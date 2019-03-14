/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.automation.integration;

import org.junit.Test;
import org.mule.runtime.api.el.BindingContext;
import org.mule.runtime.api.metadata.DataType;
import org.mule.runtime.api.metadata.TypedValue;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class SpecialActionOperationsTestCase extends AbstractTestCase {

    @Override
    protected String getConfigFile() {
        return "flows/special-action-operations-flows.xml";
    }

    @Test()
    public void approveTest() throws Exception {

        final String ID = "24";

        try {
            TypedValue<String> payload = flowRunner("approve")
                    .withPayload(ID)
                    .run()
                    .getMessage()
                    .getPayload();
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

    }

    @Test()
    public void inventoryTransactionVoidTest() throws Exception {

        TypedValue<String> payload = flowRunner("it_void")
                .run()
                .getMessage()
                .getPayload();

        BindingContext bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload, DataType.OBJECT))
                .build();
        Map<String, Object> value =
                (Map<String, Object>) expressionManager.evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext).getValue() ;
        assertThat(value, hasEntry("status", "voided"));

    }

    @Test()
    public void invoiceVoidTest() throws Exception {

        TypedValue<String> payload = flowRunner("invoice_void")
                .run()
                .getMessage()
                .getPayload();

        BindingContext bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload, DataType.OBJECT))
                .build();
        Map<String, Object> value =
                (Map<String, Object>) expressionManager.evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext).getValue() ;
        assertThat(value, hasEntry("status", "voided"));

    }

    @Test()
    public void purchaseOrderCloseTest() throws Exception {

        TypedValue<String> payload = flowRunner("po_close")
                .run()
                .getMessage()
                .getPayload();

        BindingContext bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload, DataType.OBJECT))
                .build();
        Map<String, Object> value =
                (Map<String, Object>) expressionManager.evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext).getValue() ;
        assertThat(value, hasEntry("status", "closed"));

    }

    @Test
    public void createDraftRequisitionTest() throws Exception {

        String json = "{\n" +
                "\t\"buyer-note\": \"hi\",\n" +
                "\t\"requested-by\": {\n" +
                "\t\t\"id\": 2\n" +
                "\t}\n" +
                "}";

        TypedValue<String> payload = flowRunner("requisition_draft")
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
        assertThat(value, hasEntry("buyer-note", "hi"));
    }


}
