/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.internal.connection.api;

import org.mule.extension.coupa.internal.connection.CoupaConnection;

public class Addresses extends BasicApi {

    private static final String ADDRESSES_PATH = "addresses";

    public Addresses(CoupaConnection coupaConnection) {
        super(coupaConnection);
    }

    @Override
    String getPath() {
        return ADDRESSES_PATH;
    }

    @Override
    String getSubPath() {
        return null;
    }
}
