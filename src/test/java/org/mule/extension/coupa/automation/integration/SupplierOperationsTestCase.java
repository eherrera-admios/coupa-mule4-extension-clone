/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.automation.integration;

import org.junit.Before;
import org.junit.Test;
import org.mule.runtime.api.el.BindingContext;
import org.mule.runtime.api.metadata.DataType;
import org.mule.runtime.api.metadata.TypedValue;

import java.time.Instant;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SupplierOperationsTestCase extends AbstractTestCase {

    private static final String GET_FIRST_OBJECT_EXPRESSION = "#[payload[0]]";

    @Override
    protected String getConfigFile() {
        return "flows/supplier-operations-flows.xml";
    }

    private static Integer supplierId;

    @Before
    public void getSupplierId() throws Exception {

        TypedValue<String> payload = flowRunner("supplier_query")
                .keepStreamsOpen()
                .run()
                .getMessage()
                .getPayload();

        BindingContext bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload, DataType.OBJECT))
                .build();
        Map<String, Object> value = (Map<String, Object>) expressionManager.evaluate(GET_FIRST_OBJECT_EXPRESSION, bindingContext).getValue();

        supplierId = (Integer) value.get("id");
        System.out.println("SUPPLIER ID SET TO: " + supplierId);

    }

    @Test
    public void createRemitToTest() throws Exception {

        final String REMIT_TO_CODE = Instant.now().toString();

        String json = "{\n" +
                "\t\"name\": \"new Test Address\",\n" +
                "\t\"country\": { \"id\": 10 },\n" +
                "\t\"city\": \"SK\",\n" +
                "\t\"remit-to-code\": \"" + REMIT_TO_CODE + "\",\n" +
                "\t\"postal-code\": \"1000\",\n" +
                "\t\"street1\": \"vasko karangeleski\"\n" +
                "}";

        TypedValue<String> payload = flowRunner("remit_create")
                .withPayload(json)
                .withVariable("supplierId", supplierId)
                .run()
                .getMessage()
                .getPayload();

        BindingContext bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload, DataType.OBJECT))
                .build();
        Map<String, Object> value =
                (Map<String, Object>) expressionManager.evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext).getValue();

        assertTrue(value.containsKey("id"));

        String codeReturned = (String) value.get("remit_to_code");
        assertThat(REMIT_TO_CODE, is(codeReturned));

    }

    @Test
    public void queryRemitToTest() throws Exception {

        TypedValue<String> payload = flowRunner("remit_query")
                .withVariable("supplierId", supplierId)
                .keepStreamsOpen()
                .run()
                .getMessage()
                .getPayload();

        BindingContext bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload, DataType.OBJECT))
                .build();
        Map<String, Object> value = (Map<String, Object>) expressionManager.evaluate(GET_FIRST_OBJECT_EXPRESSION, bindingContext).getValue();

        assertTrue(value.containsKey("id"));
        Object remitToId = value.get("id");

        TypedValue<String> payload2 = flowRunner("remit_get")
                .withVariable("supplierId", supplierId)
                .withVariable("remitToId", remitToId)
                .run()
                .getMessage()
                .getPayload();

        BindingContext bindingContext2 = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload2, DataType.OBJECT))
                .build();
        value = (Map<String, Object>) expressionManager.evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext2).getValue();

        assertTrue(value.containsKey("id"));
        assertThat(remitToId, is((Integer)value.get("id")));

    }

}
