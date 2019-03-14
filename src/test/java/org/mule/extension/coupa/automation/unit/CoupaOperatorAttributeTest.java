/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.automation.unit;

import org.junit.Before;
import org.junit.Test;
import org.mule.extension.coupa.api.CoupaOperator;
import org.mule.extension.coupa.api.CoupaOperatorAttribute;

import static org.junit.Assert.assertEquals;

public class CoupaOperatorAttributeTest {

    private CoupaOperatorAttribute coa;

    @Before
    public void setUp() {
        coa = new CoupaOperatorAttribute();
        coa.setField("field");
        coa.setOperator(CoupaOperator.eq);
        coa.setValue("value");
    }

    @Test
    public void testSetField() {
        coa.setField("field");
    }

    @Test
    public void testGetField() {
        assertEquals("field", coa.getField());
    }

    @Test
    public void testSetOperator() {
        coa.setOperator(CoupaOperator.eq);
    }

    @Test
    public void testGetOperator() {
        assertEquals(CoupaOperator.eq, coa.getOperator());
    }

    @Test
    public void testSetValue() {
        coa.setValue("value");
    }

    @Test
    public void testGetValue() {
        assertEquals("value", coa.getValue());
    }
}
