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
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BasicOperationsUpdateTestCase extends AbstractTestCase {

    private static final String JAVA_PAYLOAD_EXPRESSION2 = "#[output application/java input payload application/java --- payload]";

    @Test
    public void updateAddressTest() throws Exception {

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

        Map<String, Object> map = (Map<String, Object>) expressionManager
                        .evaluate(JAVA_PAYLOAD_EXPRESSION2, bindingContext)
                        .getValue();

        assertThat(map, hasEntry("id", Integer.parseInt(ID)));
        assertTrue(map.containsKey("street1"));
        String json = "{\"street1\": \"" + timestamp + "\"}";

        TypedValue<String> updated = flowRunner("update-object")
                .withVariable("objectType", Constants.ADDRESS_KEY)
                .withVariable("id", ID)
                .withPayload(json)
                .run()
                .getMessage()
                .getPayload();

        bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(updated, DataType.OBJECT))
                .build();
        map = (Map<String, Object>) expressionManager
                        .evaluate(JAVA_PAYLOAD_EXPRESSION2, bindingContext)
                        .getValue();
        assertThat(map, hasEntry("id", Integer.parseInt(ID)));
        assertTrue(map.containsKey("street1"));
        assertEquals(map.get("street1"), timestamp);
    }

    @Test
    public void updateLookupValueTest() throws Exception {

        final String ID = "1";

        Event event = flowRunner("get-object")
                .withPayload(ID)
                .withVariable("objectType", Constants.LOOKUP_VALUE_KEY)
                .run();
        TypedValue<String> payload = event.getMessage().getPayload();

        BindingContext bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload, DataType.OBJECT))
                .build();
        Map<String, Object> map =
                (Map<String, Object>) expressionManager.evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext).getValue() ;

        assertThat(map, hasEntry("id", Integer.parseInt(ID)));

        String json = "{\"name\": \"" + timestamp + "\"}";

        TypedValue<String> updated = flowRunner("update-object")
                .withVariable("objectType", Constants.LOOKUP_VALUE_KEY)
                .withVariable("id", ID)
                .withPayload(json)
                .run()
                .getMessage()
                .getPayload();

        bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(updated, DataType.OBJECT))
                .build();
        map = (Map<String, Object>) expressionManager
                .evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext)
                .getValue();
        assertThat(map, hasEntry("id", Integer.parseInt(ID)));
        assertTrue(map.containsKey("name"));
        assertEquals(map.get("name"), timestamp);

    }

    @Test
    public void updatePurchaseOrderTest() throws Exception {

        final String ID = "4968";

        Event event = flowRunner("get-object")
                .withPayload(ID)
                .withVariable("objectType", Constants.PURCHASE_ORDER_KEY)
                .run();
        TypedValue<String> payload = event.getMessage().getPayload();

        BindingContext bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload, DataType.OBJECT))
                .build();
        Map<String, Object> map =
                (Map<String, Object>)
                        expressionManager.evaluate(JAVA_PAYLOAD_EXPRESSION2, bindingContext).getValue() ;

        assertThat(map, hasEntry("id", Integer.parseInt(ID)));

        Boolean priceHidden = Boolean.valueOf(map.get("price-hidden").toString());
        String json = "{\"price-hidden\": " + !priceHidden + "}";

        TypedValue<String> updated = flowRunner("update-object")
                .withVariable("objectType", Constants.PURCHASE_ORDER_KEY)
                .withVariable("id", ID)
                .withPayload(json)
                .run()
                .getMessage()
                .getPayload();

        bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(updated, DataType.OBJECT))
                .build();
        map = (Map<String, Object>) expressionManager
                .evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext)
                .getValue();
        assertThat(map, hasEntry("id", Integer.parseInt(ID)));
        assertTrue(map.containsKey("price-hidden"));
        assertEquals(map.get("price-hidden"), !priceHidden);

    }

    @Test
    public void updateSupplierTest() throws Exception {

        final String ID = "1";

        Event event = flowRunner("get-object")
                .withPayload(ID)
                .withVariable("objectType", Constants.SUPPLIER_KEY)
                .run();
        TypedValue<String> payload = event.getMessage().getPayload();

        BindingContext bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload, DataType.OBJECT))
                .build();
        Map<String, Object> map =
                (Map<String, Object>) expressionManager.evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext).getValue() ;

        assertThat(map, hasEntry("id", Integer.parseInt(ID)));

        String json = "{\"name\": \"" + timestamp + "\"}";

        TypedValue<String> updated = flowRunner("update-object")
                .withVariable("objectType", Constants.SUPPLIER_KEY)
                .withVariable("id", ID)
                .withPayload(json)
                .run()
                .getMessage()
                .getPayload();

        bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(updated, DataType.OBJECT))
                .build();
        map = (Map<String, Object>) expressionManager
                .evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext)
                .getValue();
        assertThat(map, hasEntry("id", Integer.parseInt(ID)));
        assertTrue(map.containsKey("name"));
        assertEquals(map.get("name"), timestamp);

    }

    @Test
    public void updateUserTest() throws Exception {

        final String ID = "6";

        Event event = flowRunner("get-object")
                .withPayload(ID)
                .withVariable("objectType", Constants.USER_KEY)
                .run();
        TypedValue<String> payload = event.getMessage().getPayload();

        BindingContext bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(payload, DataType.OBJECT))
                .build();
        Map<String, Object> map =
                (Map<String, Object>) expressionManager.evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext).getValue() ;

        assertThat(map, hasEntry("id", Integer.parseInt(ID)));

        String json = "{\"login\": \"" + timestamp + "\"}";

        TypedValue<String> updated = flowRunner("update-object")
                .withVariable("objectType", Constants.USER_KEY)
                .withVariable("id", ID)
                .withPayload(json)
                .run()
                .getMessage()
                .getPayload();

        bindingContext = BindingContext.builder()
                .addBinding("payload", new TypedValue<>(updated, DataType.OBJECT))
                .build();
        map = (Map<String, Object>) expressionManager
                .evaluate(JAVA_PAYLOAD_EXPRESSION, bindingContext)
                .getValue();
        assertThat(map, hasEntry("id", Integer.parseInt(ID)));
        assertTrue(map.containsKey("login"));
        assertEquals(map.get("login"), timestamp);

    }

}
