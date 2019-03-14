/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.automation.integration;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.mule.runtime.api.component.location.Location;
import org.mule.runtime.api.connection.ConnectionValidationResult;
import org.mule.runtime.api.connectivity.ConnectivityTestingService;
import org.mule.tck.util.TestConnectivityUtils;

import javax.inject.Inject;
import javax.inject.Named;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.assertFalse;
import static org.mule.runtime.api.connectivity.ConnectivityTestingService.CONNECTIVITY_TESTING_SERVICE_KEY;

public class ConnectionTestCase extends MuleArtifactFunctionalTestCase {

    @Inject
    @Named(CONNECTIVITY_TESTING_SERVICE_KEY)
    ConnectivityTestingService connectivityTestingService;

    @Rule
    public TestRule timeout = TestConnectivityUtils.disableAutomaticTestConnectivity();


    @Override
    public int getTestTimeoutSecs() {
        return 999999;
    }


    @Override
    protected String getConfigFile() {
        return "flows/connection-test.xml";
    }


    @Test
    public void connectWithValidCredentialsTest() {
        ConnectionValidationResult validationResult = connectivityTestingService.testConnection(Location.builder()
                .globalName("coupaValidCredentials").build());

        assertThat(validationResult.isValid(), is(true));
    }

    @Test
    public void connectWithInvalidCredentialsTest() {
        ConnectionValidationResult validationResult = connectivityTestingService.testConnection(Location.builder()
                .globalName("coupaConfigInvalidCredentials").build());
        assertFalse(validationResult.isValid());
    }

    @Test
    public void connectWithInvalidInstance() {
        ConnectionValidationResult validationResult = connectivityTestingService.testConnection(Location.builder()
                .globalName("coupaConfigInvalidInstance").build());
        assertFalse(validationResult.isValid());
    }

    @Test
    public void connectWithInvalidApiKeyTest() {
        ConnectionValidationResult validationResult = connectivityTestingService.testConnection(Location.builder()
                .globalName("coupaConfigInvalidApiKey").build());
        assertFalse(validationResult.isValid());
    }

}
