/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.internal.metadata;

import org.mule.runtime.api.metadata.MetadataKey;
import org.mule.runtime.api.metadata.MetadataKeyBuilder;

import java.util.*;

import static org.mule.extension.coupa.internal.utils.Constants.*;

public class BasicKeysResolver {

    private BasicKeysResolver() {}

    public static Set<MetadataKey> buildKeys(Map<String, String> keysMap) {
        HashSet<MetadataKey> keys = new HashSet<>();
        for (Map.Entry<String, String> entry : keysMap.entrySet()) {
            MetadataKeyBuilder keyBuilder = MetadataKeyBuilder.newKey(entry.getKey());
            keyBuilder.withDisplayName(entry.getValue());
            keys.add(keyBuilder.build());
        }
        return keys;
    }

    public static Set<MetadataKey> buildDefaultKeys() {
        Map<String, String> defaultKeysMap = new HashMap<>();
        defaultKeysMap.put(INVOICE_KEY, INVOICE);
        defaultKeysMap.put(APPROVAL_KEY, APPROVAL);
        defaultKeysMap.put(PURCHASE_ORDER_KEY, PURCHASE_ORDER);
        defaultKeysMap.put(SUPPLIER_KEY, SUPPLIER);
        defaultKeysMap.put(REQUISITION_KEY, REQUISITION);
        defaultKeysMap.put(LOOKUP_VALUE_KEY, LOOKUP_VALUE);
        defaultKeysMap.put(ADDRESS_KEY, ADDRESS);
        defaultKeysMap.put(EXPENSE_REPORT_KEY, EXPENSE_REPORT);
        defaultKeysMap.put(INVENTORY_TRANSACTION_KEY, INVENTORY_TRANSACTION);
        defaultKeysMap.put(USER_KEY, USER);
        return buildKeys(defaultKeysMap);
    }

}
