/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.internal.service;

import org.apache.commons.io.IOUtils;
import org.mule.metadata.api.builder.ArrayTypeBuilder;
import org.mule.metadata.api.builder.BaseTypeBuilder;
import org.mule.metadata.api.builder.ObjectTypeBuilder;
import org.mule.metadata.api.model.MetadataFormat;
import org.mule.metadata.api.model.MetadataType;
import org.mule.runtime.api.metadata.MetadataResolvingException;
import org.mule.runtime.api.metadata.resolving.FailureCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;
import java.util.Properties;

public class CoupaMetadataResolver {

    private final static Logger logger = LoggerFactory.getLogger(CoupaMetadataResolver.class);
    private final static String COUPA_METADATA_FILE = "coupaMetadata.properties";
    private static Properties prop = null;

    public static MetadataType generateMetaData(String key, String customSchemaString, boolean isCollection) throws MetadataResolvingException {

        updateProps();

        if (prop != null) {
            String metadata = prop.getProperty(key);
            List<CoupaMetadata> coupaMetadataList = CoupaMetadata.getEntityMetadataList(metadata, key, customSchemaString);

            if (isCollection) {
                ObjectTypeBuilder objectTypeBuilder = BaseTypeBuilder.create(MetadataFormat.JSON).objectType().id(key);
                coupaMetadataList.forEach(coupaMetadata -> addFields(objectTypeBuilder, coupaMetadata, prop, customSchemaString));
                MetadataType objectMetadata = objectTypeBuilder.build();
                ArrayTypeBuilder arrayBuilder = BaseTypeBuilder.create(MetadataFormat.JSON).arrayType().of(objectMetadata).id(key+"-results");
                return arrayBuilder.build();
            } else {
                ObjectTypeBuilder objectTypeBuilder = BaseTypeBuilder.create(MetadataFormat.JSON).objectType().id(key);
                coupaMetadataList.forEach(coupaMetadata -> addFields(objectTypeBuilder, coupaMetadata, prop, customSchemaString));
                return objectTypeBuilder.build();
            }

        }

        return null;

    }

    private static synchronized void updateProps() throws MetadataResolvingException {
        if (prop == null || prop.isEmpty())
            prop = reloadProperties();
    }

    private static void addFields(ObjectTypeBuilder object, CoupaMetadata coupaMetadata, Properties prop, String customSchemaString) {

        if (coupaMetadata.getDataType() == CoupaMetadata.CoupaMetadataDataType.Reference) {

            String referenceMetadataString = prop.getProperty(coupaMetadata.getReferencedType());
            List<CoupaMetadata> referenceCoupaMetadata = CoupaMetadata.getEntityMetadataList(referenceMetadataString, coupaMetadata.getReferencedType(), customSchemaString);

            if (coupaMetadata.getType() == CoupaMetadata.CoupaMetadataFieldType.Simple) {
                ObjectTypeBuilder refObject = object.addField().key(coupaMetadata.getName()).value().objectType();
                referenceCoupaMetadata.forEach(subCoupaMetadata -> addFields(refObject, subCoupaMetadata, prop, customSchemaString));
            } else if (coupaMetadata.getType() == CoupaMetadata.CoupaMetadataFieldType.List) {
                ObjectTypeBuilder objectOfArray = object.addField().key(coupaMetadata.getName()).value().arrayType().of().objectType();
                referenceCoupaMetadata.forEach(subCoupaMetadata -> addFields(objectOfArray, subCoupaMetadata, prop, customSchemaString));
            }

        } else {

            BaseTypeBuilder typeBuilder = null;

            if (coupaMetadata.getType() == CoupaMetadata.CoupaMetadataFieldType.Simple)
                typeBuilder = object.addField().key(coupaMetadata.getName()).value();
            if (coupaMetadata.getType() == CoupaMetadata.CoupaMetadataFieldType.List)
                typeBuilder = object.addField().key(coupaMetadata.getName()).value().arrayType().of();

            switch (coupaMetadata.getDataType()) {
                case String:
                    typeBuilder.stringType().build();
                    break;
                case Boolean:
                    typeBuilder.booleanType().build();
                    break;
                case Date:
                    typeBuilder.dateType().build();
                    break;
                case Long:
                case Integer:
                case Float:
                case Double:
                case Decimal:
                    typeBuilder.numberType().build();
                    break;
                case Date_Time:
                    typeBuilder.dateTimeType().build();
                    break;
                default:
                    typeBuilder.anyType().build();

            }

        }

    }

    private static Properties reloadProperties() throws MetadataResolvingException {

        Properties prop = new Properties();

        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(COUPA_METADATA_FILE)) {

            StringWriter writer = new StringWriter();
            IOUtils.copy(input, writer, "UTF-8");

            prop.load(IOUtils.toInputStream(writer.toString(), "UTF-8"));

            return prop;


        } catch (IOException ex) {
            logger.error("Cannot reload static metadata file", ex);
            throw new MetadataResolvingException("Error generating datasense", FailureCode.RESOURCE_UNAVAILABLE, ex);
        }

    }

}
