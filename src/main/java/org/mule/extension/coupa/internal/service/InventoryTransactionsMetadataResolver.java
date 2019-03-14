/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.internal.service;

import org.mule.extension.coupa.internal.config.CoupaConf;
import org.mule.metadata.api.model.MetadataType;
import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.metadata.MetadataContext;
import org.mule.runtime.api.metadata.MetadataResolvingException;
import org.mule.runtime.api.metadata.resolving.OutputTypeResolver;

import static org.mule.extension.coupa.internal.utils.Constants.INVENTORY_TRANSACTION_KEY;

public class InventoryTransactionsMetadataResolver implements OutputTypeResolver<String> {

    @Override
    public String getCategoryName() {
        return "inventory-transactions-actions";
    }

    @Override
    public MetadataType getOutputType(MetadataContext context, String key) throws MetadataResolvingException, ConnectionException {
        CoupaConf conf = (CoupaConf) context.getConfig().get();
        String customFields = conf.getCustomFieldsStringByKey(key);
        return CoupaMetadataResolver.generateMetaData(INVENTORY_TRANSACTION_KEY, customFields, false);

    }

}
