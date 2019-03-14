/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.internal.connection;

import org.mule.runtime.api.metadata.TypedValue;
import org.mule.runtime.api.util.MultiMap;
import org.mule.runtime.core.api.util.IOUtils;
import org.mule.runtime.http.api.HttpConstants;
import org.mule.runtime.http.api.client.HttpClient;
import org.mule.runtime.http.api.domain.entity.ByteArrayHttpEntity;
import org.mule.runtime.http.api.domain.entity.multipart.HttpPart;
import org.mule.runtime.http.api.domain.entity.multipart.MultipartHttpEntity;
import org.mule.runtime.http.api.domain.message.request.HttpRequest;
import org.mule.runtime.http.api.domain.message.request.HttpRequestBuilder;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

import static java.util.Collections.singleton;
import static org.mule.extension.coupa.internal.utils.CoupaValidation.buildValidInstanceUri;
import static org.mule.runtime.extension.api.annotation.param.MediaType.APPLICATION_JSON;

public class CoupaRequestBuilderFactory {

    private final static Logger logger = LoggerFactory.getLogger(CoupaRequestBuilderFactory.class);

    private static final String API_HEADER = "X-COUPA-API-KEY";
    private static final String ACCEPT = "Accept";

    private final HttpClient httpClient;
    private final String instance;
    private final String apiKey;
    private final int responseTimeout;

    public CoupaRequestBuilderFactory(String instance, String apiKey,  HttpClient httpClient) {
        this.instance = buildValidInstanceUri(instance);
        this.apiKey = apiKey;
        this.httpClient = httpClient;
        this.responseTimeout = 120000;
    }

    public CoupaRequestBuilder newRequest(String path) {
        return new CoupaRequestBuilder(path);
    }

    public CoupaRequestBuilder newRequest(String path, MultiMap<String, String> params) {
        return new CoupaRequestBuilder(path, params);
    }

    public CoupaRequestBuilder newRequest(String path, String entity) {
        return new CoupaRequestBuilder(path, entity);
    }

    public CoupaRequestBuilder newRequest(String path, TypedValue<InputStream> inputStream) {
        return new CoupaRequestBuilder(path, inputStream);
    }

    public class CoupaRequestBuilder {

        private final String uri;
        private final MultiMap<String, String> params;
        private final MultiMap<String, String> headers;
        private TypedValue<InputStream> streamEntity;
        private String entity;

        CoupaRequestBuilder(String path) {
            this.uri = instance + path;
            this.headers = new MultiMap<>();
            this.params = new MultiMap<>();
            this.headers.put(API_HEADER, apiKey);
            this.headers.put(ACCEPT, APPLICATION_JSON);
            this.entity = null;
            this.streamEntity = null;
        }

        CoupaRequestBuilder(String path, MultiMap<String, String> params) {
            this(path);
            this.params.putAll(params);
        }

        CoupaRequestBuilder(String path, String entity) {
            this(path);
            this.entity = entity;
        }

        CoupaRequestBuilder(String path, TypedValue<InputStream> entity) {
            this(path);
            this.streamEntity = entity;
        }

        HttpResponse sendRequest(HttpConstants.Method method) throws IOException, TimeoutException {

            HttpRequest httpRequest = HttpRequest.builder()
                    .method(method)
                    .uri(uri)
                    .queryParams(params)
                    .headers(headers)
                    .build();

            logger.info("Copua API Request: {} {}", method.name(), httpRequest.getUri().toString());

            return httpClient.send(httpRequest, responseTimeout, true, null);
        }

        CompletableFuture<HttpResponse> sendAsyncRequest(HttpConstants.Method method) {

            HttpRequestBuilder httpRequestBuilder = HttpRequest.builder();

            if (entity != null) {
                byte[] byteArray = entity.getBytes(StandardCharsets.UTF_8);
                httpRequestBuilder = httpRequestBuilder.entity(new ByteArrayHttpEntity(byteArray));
            }

            if (streamEntity != null) {
                byte[] byteArrayContent = IOUtils.toByteArray(streamEntity.getValue());
                MultipartHttpEntity httpEntity = new MultipartHttpEntity(singleton(
                        new HttpPart("file", "viewable_invoice.png", byteArrayContent, streamEntity.getDataType().getMediaType().toString(), byteArrayContent.length)));

                httpRequestBuilder = httpRequestBuilder.entity(httpEntity);
            }

            HttpRequest request = httpRequestBuilder
                    .method(method)
                    .uri(uri)
                    .queryParams(params)
                    .headers(headers)
                    .build();

            logger.info("Copua API Request: {} {}", method.name(), request.getUri().toString());

            return httpClient.sendAsync(request, responseTimeout, true, null);
        }

    }

}
