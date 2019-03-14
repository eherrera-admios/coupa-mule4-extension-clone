/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.internal.utils;

import org.apache.commons.io.IOUtils;
import org.mule.runtime.api.metadata.TypedValue;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class CoupaValidation {

    public static String getEntityString(TypedValue<InputStream> payload) throws IOException {

        if (payload == null)
            return null;

        InputStream value = payload.getValue();
        if (value == null)
            return null;

        if (payload.getByteLength().isPresent() && payload.getByteLength().getAsLong() == 0) {
            return null;
        }

        String stringValue = IOUtils.toString(value, StandardCharsets.UTF_8.name());
        if (stringValue == null || stringValue.isEmpty())
            return null;

        return stringValue;

    }

    public static String buildValidInstanceUri(String instance) {

        String instanceUri = instance;

        if (!instanceUri.startsWith("https")) {
            if (!instanceUri.startsWith("http"))
                instanceUri = "https://" + instanceUri;
            else
                instanceUri = instanceUri.replace("http", "https");
        }

        if (!instanceUri.endsWith("/"))
            instanceUri = instanceUri + "/";

        if (!instanceUri.endsWith("api/"))
            instanceUri = instanceUri + "api/";

        return instanceUri;

    }
}
