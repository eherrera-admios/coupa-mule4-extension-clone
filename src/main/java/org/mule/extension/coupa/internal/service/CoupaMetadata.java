/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.internal.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoupaMetadata {
	
	private String name;
	private CoupaMetadataFieldType type;
	private CoupaMetadataDataType dataType;
	private String referencedType;

	public enum CoupaMetadataFieldType {

		Simple, List

	}

	public enum CoupaMetadataDataType {

		String, Date, Date_Time, Integer, Double, Float, Long, Decimal, Boolean, Reference

	}
	
	public CoupaMetadata() {
		super();
	}
	
	public CoupaMetadata(String metadataString) {
		
		String[] items = metadataString.split(",");
		this.name = items[0];
		this.type = CoupaMetadataFieldType.valueOf(items[1]);
		
		String dataTypeString = items[2];
		if (dataTypeString.startsWith("Reference")) {
			this.dataType = CoupaMetadataDataType.Reference;
			this.referencedType = dataTypeString.split(":")[1];
		} else {
			this.dataType = CoupaMetadataDataType.valueOf(dataTypeString);
		}
		
	}
	
	public static CoupaMetadata createCustomMetadata(String metadataString) {
		
		CoupaMetadata cutomMetadata = new CoupaMetadata();
		
		String[] items = metadataString.split(",");
		cutomMetadata.setName(items[0].trim());
		cutomMetadata.setType(CoupaMetadataFieldType.Simple);
		
		String dataTypeString = items[1].trim();
		if (dataTypeString.startsWith("Reference")) {
			cutomMetadata.setDataType(CoupaMetadataDataType.Reference);
			cutomMetadata.setReferencedType(dataTypeString.split(":")[1].trim());
		} else {
			cutomMetadata.setDataType(CoupaMetadataDataType.valueOf(dataTypeString));
		}
		
		return cutomMetadata;
		
	}
	
	static List<CoupaMetadata> getEntityMetadataList(String entityMetadata, String key, String customSchemaString) {
		
		List<CoupaMetadata> list = new ArrayList<>();
		
		Arrays.asList(entityMetadata.split(";")).forEach(metadata -> list.add(new CoupaMetadata(metadata)));

		if (customSchemaString != null) {
			Arrays.asList(customSchemaString.split(";")).forEach(customMetadata -> list.add(CoupaMetadata.createCustomMetadata(customMetadata)));
		}
		
		return list;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CoupaMetadataFieldType getType() {
		return type;
	}

	private void setType(CoupaMetadataFieldType type) {
		this.type = type;
	}

	public CoupaMetadataDataType getDataType() {
		return dataType;
	}

	private void setDataType(CoupaMetadataDataType dataType) {
		this.dataType = dataType;
	}

	public String getReferencedType() {
		return referencedType;
	}

	private void setReferencedType(String referencedType) {
		this.referencedType = referencedType;
	}
	
}
