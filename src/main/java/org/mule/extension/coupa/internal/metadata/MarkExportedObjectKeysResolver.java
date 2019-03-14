/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.internal.metadata;

import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.metadata.MetadataContext;
import org.mule.runtime.api.metadata.MetadataKey;
import org.mule.runtime.api.metadata.MetadataResolvingException;
import org.mule.runtime.api.metadata.resolving.TypeKeysResolver;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.mule.extension.coupa.internal.metadata.BasicKeysResolver.buildKeys;
import static org.mule.extension.coupa.internal.utils.Constants.*;

public class MarkExportedObjectKeysResolver implements TypeKeysResolver {

    @Override
    public String getCategoryName() {
        return "objects-export";
    }

    @Override
    public Set<MetadataKey> getKeys(MetadataContext metadataContext) throws MetadataResolvingException, ConnectionException {

        Map<String, String> markExportedObjectKeysMap = new HashMap<>();

        markExportedObjectKeysMap.put(EXPENSE_REPORT_KEY, EXPENSE_REPORT);
        markExportedObjectKeysMap.put(INVENTORY_TRANSACTION_KEY, INVENTORY_TRANSACTION);
        markExportedObjectKeysMap.put(INVOICE_KEY, INVOICE);
        markExportedObjectKeysMap.put(PURCHASE_ORDER_KEY, PURCHASE_ORDER);
        markExportedObjectKeysMap.put(REQUISITION_KEY, REQUISITION);

        return buildKeys(markExportedObjectKeysMap);
    }

}
