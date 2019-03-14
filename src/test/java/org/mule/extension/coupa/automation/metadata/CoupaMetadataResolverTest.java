/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.automation.metadata;

import org.junit.Test;
import org.mule.extension.coupa.internal.service.CoupaMetadataResolver;
import org.mule.metadata.api.model.MetadataType;
import org.mule.runtime.api.metadata.MetadataResolvingException;

import static org.junit.Assert.assertNotNull;

public class CoupaMetadataResolverTest {

    @Test
    public void testGenerateMetadata() throws MetadataResolvingException {
        String customMeta = "field-one,String; field-two,Integer;";

        MetadataType mt = CoupaMetadataResolver.generateMetaData("user", customMeta, false);
        assertNotNull(mt);

        mt = CoupaMetadataResolver.generateMetaData("account-type-summary", customMeta, false);
        assertNotNull(mt);

        mt = CoupaMetadataResolver.generateMetaData("requisition", customMeta, false);
        assertNotNull(mt);

        mt = CoupaMetadataResolver.generateMetaData("invoice", customMeta, false);
        assertNotNull(mt);

        mt = CoupaMetadataResolver.generateMetaData("user", customMeta, true);
        assertNotNull(mt);
    }

}
