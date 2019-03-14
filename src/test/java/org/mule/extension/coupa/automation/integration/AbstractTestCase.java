/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.automation.integration;

import org.junit.Before;
import org.junit.Rule;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.mule.runtime.core.api.el.ExpressionManager;
import org.mule.tck.junit4.rule.SystemProperty;
import org.mule.tck.util.TestConnectivityUtils;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class AbstractTestCase extends MuleArtifactFunctionalTestCase {

    static final String JAVA_PAYLOAD_EXPRESSION = "#[output application/java --- payload]";

    @Inject
    ExpressionManager expressionManager;

    @Rule
    public SystemProperty rule2 = TestConnectivityUtils.disableAutomaticTestConnectivity();

    @Override
    protected String getConfigFile() {
        return "flows/basic-operation-flows.xml";
    }

    @Override
    public int getTestTimeoutSecs() {
        return 600;
    }

    String timestamp;

    @Before
    public void setUp() throws Exception {

        timestamp = DateTimeFormatter.ofPattern("yyyyMMddhhmmss").format(LocalDateTime.now());

    }

}