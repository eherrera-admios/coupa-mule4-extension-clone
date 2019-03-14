/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.internal.operation;

import org.mule.extension.coupa.internal.CoupaError;
import org.mule.extension.coupa.internal.connection.CoupaConnection;
import org.mule.extension.coupa.internal.error.CoupaException;
import org.mule.extension.coupa.internal.service.GetRemitToAddressArrayMetadata;
import org.mule.extension.coupa.internal.service.GetRemitToAddressMetadata;
import org.mule.extension.coupa.internal.utils.CoupaValidation;
import org.mule.runtime.api.metadata.TypedValue;
import org.mule.runtime.extension.api.annotation.metadata.OutputResolver;
import org.mule.runtime.extension.api.annotation.metadata.TypeResolver;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.Content;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Summary;
import org.mule.runtime.extension.api.runtime.process.CompletionCallback;
import org.mule.runtime.extension.api.runtime.streaming.PagingProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static org.mule.extension.coupa.internal.utils.Constants.SUPPLIER_KEY;
import static org.mule.runtime.extension.api.annotation.param.MediaType.APPLICATION_JSON;

public class SuppliersApiOperations extends CoupaOperations {

    private final static Logger logger = LoggerFactory.getLogger(SuppliersApiOperations.class);

    @MediaType(value = APPLICATION_JSON)
    @DisplayName("Suppliers | Get Remit-To Address")
    @OutputResolver(output = GetRemitToAddressMetadata.class)
    public void getSuppliersRemitToAddress(@DisplayName("Supplier ID") @Summary("Enter Supplier ID") String supplierId,
                                           @DisplayName("Remit-To Address ID") @Summary("Enter Remit-To Address ID") String remitToAddressId,
                                           @Connection CoupaConnection connection,
                                           CompletionCallback<InputStream, Void> callback) {

        connection.getSuppliers()
                .getWithSubPath(supplierId, remitToAddressId)
                .whenCompleteAsync(new CoupaOperations.CoupaResponseConsumer(CoupaError.EXECUTION, callback));

    }

    @DisplayName("Suppliers | Query Remit-To Address")
    @OutputResolver(output = GetRemitToAddressArrayMetadata.class)
    public PagingProvider<CoupaConnection, Map<String, Object>> querySuppliersRemitToAddress(@DisplayName("Supplier ID") @Summary("Enter Supplier ID") String supplierId,
                                                                                             @ParameterGroup(name = "Query Parameters") QueryParams queryParams) {
        return getPagingProvider(SUPPLIER_KEY, supplierId, queryParams);

    }

    @MediaType(value = APPLICATION_JSON)
    @DisplayName("Suppliers | Update Remit-To Address")
    @OutputResolver(output = GetRemitToAddressMetadata.class)
    public void updateSuppliersRemitToAddress(@DisplayName("Supplier ID") @Summary("Enter Supplier ID") String supplierId,
                                              @DisplayName("Remit-To Address ID") @Summary("Enter Remit-To Address ID") String remitToAddressId,
                                              @Content @TypeResolver(GetRemitToAddressMetadata.class) TypedValue<InputStream> entity,
                                              @Connection CoupaConnection connection,
                                              CompletionCallback<InputStream, Void> callback) throws IOException {

        String entityString = CoupaValidation.getEntityString(entity);
        if (entityString == null) {
            logger.error("Payload cannot be null");
            throw new CoupaException("Payload cannot be null", CoupaError.INVALID_PARAMS);
        }

        connection.getSuppliers()
                .updateWithSubPath(supplierId, remitToAddressId, entityString)
                .whenCompleteAsync(new CoupaResponseConsumer(CoupaError.EXECUTION, callback));

    }

    @MediaType(value = APPLICATION_JSON)
    @DisplayName("Suppliers | Create Remit-To Address")
    @OutputResolver(output = GetRemitToAddressMetadata.class)
    public void cresteSuppliersRemitToAddress(@DisplayName("Supplier ID") @Summary("Enter Supplier ID") String supplierId,
                                              @Content @TypeResolver(GetRemitToAddressMetadata.class) TypedValue<InputStream> entity,
                                              @Connection CoupaConnection connection,
                                              CompletionCallback<InputStream, Void> callback) throws IOException {

        String entityString = CoupaValidation.getEntityString(entity);
        if (entityString == null) {
            logger.error("Payload cannot be null");
            throw new CoupaException("Payload cannot be null", CoupaError.INVALID_PARAMS);
        }

        connection.getSuppliers()
                .createWithSubPath(supplierId, entityString)
                .whenCompleteAsync(new CoupaResponseConsumer(CoupaError.EXECUTION, callback));

    }

}
