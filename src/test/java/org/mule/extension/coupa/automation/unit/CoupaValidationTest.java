/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.automation.unit;

import org.junit.Test;
import org.mule.extension.coupa.internal.utils.CoupaValidation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNull;

public class CoupaValidationTest {

    @Test
    public void testGetEntityString() throws Exception{
        assertNull(CoupaValidation.getEntityString(null));

    }

    @Test
    public void testBuildInstanceName() {

        String validInstance = "https://fake-name.cloud.com/api/";
        String noApiInstance = "https://fake-name.cloud.com/";
        String noApiNoSlashInstance = "https://fake-name.cloud.com";
        String httpInstance = "http://fake-name.cloud.com/api/";
        String noHttpInstance = "fake-name.cloud.com/api/";
        String noHttpNoApiNoSlashInstance = "fake-name.cloud.com";


        String result = CoupaValidation.buildValidInstanceUri(validInstance);
        assertThat(result, is(equalTo(validInstance)));

        result = CoupaValidation.buildValidInstanceUri(noApiInstance);
        assertThat(result, is(equalTo(validInstance)));

        result = CoupaValidation.buildValidInstanceUri(noApiNoSlashInstance);
        assertThat(result, is(equalTo(validInstance)));

        result = CoupaValidation.buildValidInstanceUri(httpInstance);
        assertThat(result, is(equalTo(validInstance)));

        result = CoupaValidation.buildValidInstanceUri(noHttpInstance);
        assertThat(result, is(equalTo(validInstance)));

        result = CoupaValidation.buildValidInstanceUri(noHttpNoApiNoSlashInstance);
        assertThat(result, is(equalTo(validInstance)));

    }

}
