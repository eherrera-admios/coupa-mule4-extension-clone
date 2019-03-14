/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.internal.operation;

import org.mule.extension.coupa.api.CoupaOperatorAttribute;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;
import org.mule.runtime.extension.api.annotation.param.display.Summary;

import java.util.List;

public class QueryParams {

    @Parameter
    @Optional
    @DisplayName("Manual Query")
    @Summary("Enter manual query")
    @Example("Example: limit=1")
    private  String query;


    @Parameter
    @Optional
    @DisplayName("Build Query")
    @Summary("Build query")
    private List<CoupaOperatorAttribute> params;

    public String getQuery() {
        return query;
    }

    public List<CoupaOperatorAttribute> getParams() {
        return params;
    }
}
