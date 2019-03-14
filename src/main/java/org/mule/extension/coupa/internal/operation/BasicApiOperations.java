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
import org.mule.extension.coupa.internal.metadata.*;
import org.mule.extension.coupa.internal.service.*;
import org.mule.extension.coupa.internal.utils.CoupaValidation;
import org.mule.extension.coupa.internal.valueprovider.StaticExportedObjectValueProvider;
import org.mule.runtime.api.metadata.TypedValue;
import org.mule.runtime.extension.api.annotation.metadata.MetadataKeyId;
import org.mule.runtime.extension.api.annotation.metadata.OutputResolver;
import org.mule.runtime.extension.api.annotation.metadata.TypeResolver;
import org.mule.runtime.extension.api.annotation.param.*;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Summary;
import org.mule.runtime.extension.api.annotation.values.OfValues;
import org.mule.runtime.extension.api.runtime.process.CompletionCallback;
import org.mule.runtime.extension.api.runtime.streaming.PagingProvider;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static org.mule.runtime.extension.api.annotation.param.MediaType.APPLICATION_JSON;

public class BasicApiOperations extends CoupaOperations {

    @MediaType(value = APPLICATION_JSON)
    @DisplayName("Get")
    @OutputResolver(output = GetObjectMetadataResolver.class)
    public void getObject(@MetadataKeyId(GetObjectKeysResolver.class) String objectType,
                          @Connection CoupaConnection connection,
                          @DisplayName("ID") @Summary("Enter ID") String id,
                          CompletionCallback<InputStream, Void> callback) {

        getApi(objectType, connection)
                .get(id)
                .whenCompleteAsync(new CoupaResponseConsumer<>(CoupaError.EXECUTION, callback));

    }

    @DisplayName("Query")
    @OutputResolver(output = QueryObjectsMetadataResolver.class)
    public PagingProvider<CoupaConnection, Map<String, Object>> queryObjects(@MetadataKeyId(QueryObjectsKeysResolver.class) String objectType,
                                                                             @ParameterGroup(name = "Query Parameters") QueryParams queryParams) {
        return getPagingProvider(objectType, null, queryParams);

    }

    @MediaType(value = APPLICATION_JSON)
    @DisplayName("Create")
    @OutputResolver(output = CreateObjectMetadataResolver.class)
    public void createObject(@MetadataKeyId(CreateObjectKeysResolver.class) String objectType,
                             @Content @TypeResolver(CreateObjectMetadataResolver.class) TypedValue<InputStream> entity,
                             @Connection CoupaConnection connection,
                             CompletionCallback<InputStream, Void> callback) throws IOException {

        String entityString = CoupaValidation.getEntityString(entity);
        if (entityString == null)
            throw new CoupaException("Payload cannot be null", CoupaError.INVALID_PARAMS);

        getApi(objectType, connection)
                .create(entityString)
                .whenCompleteAsync(new CoupaResponseConsumer<>(CoupaError.EXECUTION, callback));

    }

    @MediaType(value = APPLICATION_JSON)
    @DisplayName("Update")
    @OutputResolver(output = UpdateObjectMetadataResolver.class)
    public void updateObject(@MetadataKeyId(UpdateObjectKeysResolver.class) String objectType,
                             @DisplayName("ID") @Summary("Enter ID") String id,
                             @Content @TypeResolver(UpdateObjectMetadataResolver.class) TypedValue<InputStream> entity,
                             @Connection CoupaConnection connection,
                             CompletionCallback<InputStream, Void> callback) throws IOException {

        String entityString = CoupaValidation.getEntityString(entity);
        if (entityString == null)
            throw new CoupaException("Payload cannot be null", CoupaError.INVALID_PARAMS);

        getApi(objectType, connection)
                .update(id, entityString)
                .whenCompleteAsync(new CoupaResponseConsumer<>(CoupaError.EXECUTION, callback));

    }

    @MediaType(value = APPLICATION_JSON)
    @DisplayName("Mark exported / not exported")
    @OutputResolver(output = MarkExportedObjectMetadataResolver.class)
    public void markExportedObject(@MetadataKeyId(MarkExportedObjectKeysResolver.class) String objectType,
                                   @DisplayName("Action") @OfValues(StaticExportedObjectValueProvider.class) String actionValue,
                                   @Connection CoupaConnection connection,
                                   @DisplayName("ID") @Summary("Enter ID") String id,
                                   CompletionCallback<InputStream, Void> callback) {

        getApi(objectType, connection)
                .putSpecialAction("exported", actionValue, id)
                .whenCompleteAsync(new CoupaResponseConsumer<>(CoupaError.EXECUTION, callback));


    }
  
}
