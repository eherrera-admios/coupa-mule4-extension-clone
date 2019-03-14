/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.internal;

import org.mule.runtime.extension.api.error.ErrorTypeDefinition;

import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.mule.runtime.extension.api.error.MuleErrors.ANY;
import static org.mule.runtime.extension.api.error.MuleErrors.CONNECTIVITY;
import static org.mule.runtime.extension.api.error.MuleErrors.VALIDATION;

public enum CoupaError implements ErrorTypeDefinition<CoupaError> {

    EXECUTION(),
    INVALID_PARAMS(VALIDATION),
    INVALID_AUTH(CONNECTIVITY),
    UNKNOWN(ANY);

    private ErrorTypeDefinition parent;

    CoupaError(ErrorTypeDefinition parent) {
        this.parent = parent;
    }

    CoupaError() {

    }

    @Override
    public Optional<ErrorTypeDefinition<? extends Enum<?>>> getParent() {
        return ofNullable(parent);
    }

}
