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

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BasicOperationsMarkExportedTestCase extends AbstractTestCase {

    private static final String JAVA_PAYLOAD_EXPRESSION2 = "#[output application/java input payload application/java --- payload]";

    @Test
    public void exportExpenseReportTest() throws Exception {

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
        Map<String, Object> map =
                (Map<String, Object>) expressionManager.evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext).getValue();
        assertThat(map, hasEntry("id", Integer.parseInt(ID)));
        assertTrue(map.containsKey("exported"));
        Boolean exported = Boolean.valueOf(map.get("exported").toString());

        flowRunner("export-object")
                .withVariable("objectType", Constants.EXPENSE_REPORT_KEY)
                .withVariable("id", ID)
                .withVariable("action", Boolean.toString(!exported))
                .run()
                .getMessage()
                .getPayload();

        TypedValue<String> updated = flowRunner("get-object")
                .withVariable("objectType", Constants.EXPENSE_REPORT_KEY)
                .withPayload(ID)
                .run()
                .getMessage()
                .getPayload();
        bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(updated, DataType.OBJECT))
                .build();
        map = (Map<String, Object>) expressionManager
                .evaluate(JAVA_PAYLOAD_EXPRESSION2, bindingContext).getValue();

        assertThat(map, hasEntry("id", Integer.parseInt(ID)));
        assertTrue(map.containsKey("exported"));
        assertEquals(map.get("exported"), !exported);
    }

    @Test
    public void exportInventoryTransaction() throws Exception {

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
        Map<String, Object> map =
                (Map<String, Object>) expressionManager.evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext).getValue();
        assertThat(map, hasEntry("id", Integer.parseInt(ID)));
        assertTrue(map.containsKey("exported"));
        Boolean exported = Boolean.valueOf(map.get("exported").toString());

        flowRunner("export-object")
                .withVariable("objectType", Constants.INVENTORY_TRANSACTION_KEY)
                .withVariable("id", ID)
                .withVariable("action", Boolean.toString(!exported))
                .run();

        TypedValue<String> updated = flowRunner("get-object")
                .withVariable("objectType", Constants.INVENTORY_TRANSACTION_KEY)
                .withPayload(ID)
                .run()
                .getMessage()
                .getPayload();
        bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(updated, DataType.OBJECT))
                .build();
        map = (Map<String, Object>) expressionManager
                .evaluate(JAVA_PAYLOAD_EXPRESSION2, bindingContext).getValue();

        assertThat(map, hasEntry("id", Integer.parseInt(ID)));
        assertTrue(map.containsKey("exported"));
        assertEquals(map.get("exported"), !exported);
    }

    @Test
    public void exportInvoiceTest() throws Exception {

        final String ID = "1";

        TypedValue<String> payload = flowRunner("get-object")
                .withVariable("objectType", Constants.INVOICE_KEY)
                .withPayload(ID)
                .run()
                .getMessage()
                .getPayload();

        BindingContext bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload, DataType.OBJECT))
                .build();
        Map<String, Object> map =
                (Map<String, Object>) expressionManager.evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext).getValue();
        assertThat(map, hasEntry("id", Integer.parseInt(ID)));
        assertTrue(map.containsKey("exported"));
        Boolean exported = Boolean.valueOf(map.get("exported").toString());

        flowRunner("export-object")
                .withVariable("objectType", Constants.INVOICE_KEY)
                .withVariable("id", ID)
                .withVariable("action", Boolean.toString(!exported))
                .run()
                .getMessage()
                .getPayload();

        TypedValue<String> updated = flowRunner("get-object")
                .withVariable("objectType", Constants.INVOICE_KEY)
                .withPayload(ID)
                .run()
                .getMessage()
                .getPayload();
        bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(updated, DataType.OBJECT))
                .build();
        map = (Map<String, Object>) expressionManager
                .evaluate(JAVA_PAYLOAD_EXPRESSION2, bindingContext).getValue();

        assertThat(map, hasEntry("id", Integer.parseInt(ID)));
        assertTrue(map.containsKey("exported"));
        assertEquals(map.get("exported"), !exported);
    }

}
