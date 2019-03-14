/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.internal.connection.provider;

import org.mule.extension.coupa.internal.CoupaError;
import org.mule.extension.coupa.internal.connection.CoupaConnection;
import org.mule.extension.coupa.internal.error.CoupaException;
import org.mule.extension.coupa.internal.operation.InvoicesApiOperations;
import org.mule.runtime.api.connection.*;
import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.Password;
import org.mule.runtime.http.api.HttpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import static org.mule.extension.coupa.internal.CoupaConstants.COUPA_CONNECTION_NAME;

@Alias(COUPA_CONNECTION_NAME)
@Operations(InvoicesApiOperations.class)
public class CoupaConnectionProvider implements CachedConnectionProvider<CoupaConnection> {

  private final static Logger logger = LoggerFactory.getLogger(CoupaConnectionProvider.class);

  @Inject
  HttpService httpService;

  @Parameter
  private String coupaInstance;

  @Parameter
  @Password
  private String apiKey;

  @Override
  public CoupaConnection connect() throws ConnectionException {
    return new CoupaConnection(coupaInstance, apiKey, httpService);
  }

  @Override
  public void disconnect(CoupaConnection connection) {
    try {
      connection.invalidate();
    } catch (Exception e) {
      logger.error("Error while disconnecting [" + COUPA_CONNECTION_NAME + "] [" + coupaInstance + "]: " + e.getMessage(), e);
    }
  }

  @Override
  public ConnectionValidationResult validate(CoupaConnection connection) {
    logger.info("Checking Coupa connection to {}", coupaInstance);
    if (connection.isValid()) {
      logger.info("Connection to Coupa instance {} established", coupaInstance);
      return ConnectionValidationResult.success();
    }
    logger.error("Connection to Coupa instance {} failed!", coupaInstance);
    return ConnectionValidationResult.failure(
            "Connection failed",
            new CoupaException("Connection Error.", CoupaError.INVALID_AUTH));
  }

}
