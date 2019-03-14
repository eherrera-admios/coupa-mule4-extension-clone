/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.internal.connection.api;

import org.mule.extension.coupa.internal.connection.CoupaConnection;
import org.mule.runtime.api.util.MultiMap;
import org.mule.runtime.http.api.HttpConstants;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Users extends BasicApi {

    private final static Logger logger = LoggerFactory.getLogger(Users.class);
    private static final String USERS_PATH = "users";

    public Users(CoupaConnection coupaConnection) {
        super(coupaConnection);
    }

    public boolean testConnection() {

        MultiMap<String, String> parameterMap = new MultiMap<>();
        parameterMap.put("return_object", "limited");
        parameterMap.put("limit", "1");

        try {
            HttpResponse httpResponse = getCoupaConnection().sendSyncRequest(USERS_PATH, parameterMap, HttpConstants.Method.GET);
            return (httpResponse.getStatusCode() == HttpConstants.HttpStatus.OK.getStatusCode());
        } catch (Exception e) {
            logger.warn("Coupa Connection Failed \n", e);
            return false;
        }
        
    }

    @Override
    String getPath() {
        return USERS_PATH;
    }

    @Override
    String getSubPath() {
        return null;
    }
}
