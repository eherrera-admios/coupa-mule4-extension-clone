/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.automation.metadata;

import org.mule.extension.coupa.internal.metadata.BasicKeysResolver;
import org.junit.Test;
import org.mule.runtime.api.metadata.MetadataKey;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;


public class BasicKeyResolverTest {
    @Test
    public void testSetKeysMap() {
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        Set<MetadataKey> result = BasicKeysResolver.buildKeys(map);
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testBuildDefaultKeys() {
        Set<MetadataKey> def = BasicKeysResolver.buildDefaultKeys();
        assertNotNull(def);
        assertFalse(def.isEmpty());
        assertEquals(10, def.size());
    }
}
