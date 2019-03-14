/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.internal.valueprovider;

import org.mule.runtime.api.value.Value;
import org.mule.runtime.extension.api.values.ValueBuilder;
import org.mule.runtime.extension.api.values.ValueProvider;
import org.mule.runtime.extension.api.values.ValueResolvingException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.mule.extension.coupa.internal.utils.Constants.*;

public class StaticApprovalValueProvider implements ValueProvider {

    @Override
    public Set<Value> resolve() throws ValueResolvingException {
        Map<String, String> valueMap = new HashMap<>();
        valueMap.put(APPROVAL_APPROVE_KEY, APPROVAL_APPROVE);
        valueMap.put(APPROVAL_HOLD_KEY, APPROVAL_HOLD);
        valueMap.put(APPROVAL_REJECT_KEY, APPROVAL_REJECT);
        return ValueBuilder.getValuesFor(valueMap);
    }
}
