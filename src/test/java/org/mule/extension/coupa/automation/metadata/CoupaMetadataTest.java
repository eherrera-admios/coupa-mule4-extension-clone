/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.automation.metadata;

import org.junit.Test;
import org.mule.extension.coupa.internal.service.CoupaMetadata;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

public class CoupaMetadataTest {

    @Test
    public void testConstructor() {
        CoupaMetadata cm = new CoupaMetadata();
        assertNotNull(cm);
        assertNull(cm.getName());
        assertNull(cm.getType());
        assertNull(cm.getDataType());
        assertNull(cm.getReferencedType());

        cm = new CoupaMetadata("name,Simple,String");
        assertNotNull(cm);
        assertEquals("name", cm.getName());
        assertEquals(CoupaMetadata.CoupaMetadataFieldType.Simple, cm.getType());
        assertEquals(CoupaMetadata.CoupaMetadataDataType.String, cm.getDataType());
        assertNull("name", cm.getReferencedType());

        cm = CoupaMetadata.createCustomMetadata("name,String");
        assertNotNull(cm);
        assertEquals("name", cm.getName());
        assertEquals(CoupaMetadata.CoupaMetadataFieldType.Simple, cm.getType());
        assertEquals(CoupaMetadata.CoupaMetadataDataType.String, cm.getDataType());
        assertNull("name", cm.getReferencedType());

        cm = new CoupaMetadata("name,Simple,Reference:Date");
        assertNotNull(cm);
        assertEquals("name", cm.getName());
        assertEquals(CoupaMetadata.CoupaMetadataFieldType.Simple, cm.getType());
        assertEquals(CoupaMetadata.CoupaMetadataDataType.Reference, cm.getDataType());
        String s = cm.getReferencedType();
        assertEquals("Date", cm.getReferencedType());

    }

    @Test
    public void testCreateCustomMetadata() {
        String s = "name,Reference:Date";
        CoupaMetadata metadata = CoupaMetadata.createCustomMetadata(s);
        assertNotNull(metadata);
        assertEquals("name", metadata.getName());
        assertEquals("Date", metadata.getReferencedType());
    }
}
