/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.internal.extension;

import org.mule.extension.coupa.api.CoupaOperator;
import org.mule.extension.coupa.api.CoupaOperatorAttribute;
import org.mule.extension.coupa.internal.CoupaError;
import org.mule.extension.coupa.internal.CoupaErrorProvider;
import org.mule.extension.coupa.internal.config.CoupaConf;
import org.mule.runtime.extension.api.annotation.Configurations;
import org.mule.runtime.extension.api.annotation.Export;
import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;
import org.mule.runtime.extension.api.annotation.error.ErrorTypes;
import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.license.RequiresEnterpriseLicense;

import static org.mule.runtime.api.meta.Category.CERTIFIED;

@RequiresEnterpriseLicense(allowEvaluationLicense = true)
@Extension(name = "Coupa", vendor = "Coupa", category = CERTIFIED)
@Configurations(CoupaConf.class)
@ErrorTypes(CoupaError.class)
@Throws(CoupaErrorProvider.class)
@Export(classes = {CoupaOperator.class, CoupaOperatorAttribute.class})
@Xml(prefix = "coupa")
public class CoupaExtension {

}
