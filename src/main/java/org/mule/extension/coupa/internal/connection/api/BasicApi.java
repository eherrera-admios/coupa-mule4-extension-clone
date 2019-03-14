/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.internal.connection.api;

import org.mule.extension.coupa.api.CoupaOperator;
import org.mule.extension.coupa.api.CoupaOperatorAttribute;
import org.mule.extension.coupa.internal.connection.CoupaConnection;
import org.mule.runtime.api.metadata.TypedValue;
import org.mule.runtime.api.util.MultiMap;
import org.mule.runtime.http.api.HttpConstants;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;
import org.mule.runtime.http.api.utils.HttpEncoderDecoderUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

public abstract class BasicApi {

    private CoupaConnection coupaConnection;

    public BasicApi(CoupaConnection coupaConnection) {
        this.coupaConnection = coupaConnection;
    }

    public CoupaConnection getCoupaConnection() {
        return coupaConnection;
    }

    public void setCoupaConnection(CoupaConnection coupaConnection) {
        this.coupaConnection = coupaConnection;
    }

    abstract String getPath();
    abstract String getSubPath();

    public CompletableFuture<HttpResponse> get(String id) {

        return coupaConnection.sendAsyncRequest(getPath() + "/" + id, HttpConstants.Method.GET);

    }

    public CompletableFuture<HttpResponse> getWithSubPath(String id, String subId) {

        return coupaConnection.sendAsyncRequest(getPath() + "/" + id + "/"
                + getSubPath() + "/" + subId, HttpConstants.Method.GET);

    }

    public CompletableFuture<HttpResponse> create(String entity) {

        return coupaConnection.sendAsyncRequest(getPath(), entity, HttpConstants.Method.POST);

    }

    public CompletableFuture<HttpResponse> createWithSubPath(String id, String entity) {

        return coupaConnection.sendAsyncRequest(getPath() + "/" + id + "/" + getSubPath(), entity, HttpConstants.Method.POST);

    }

    public CompletableFuture<HttpResponse> createWithAction(String path, String action, String entity) {

        return coupaConnection.sendAsyncRequest(getPath() + "/" + path + "/" + action, entity, HttpConstants.Method.POST);

    }

    public CompletableFuture<HttpResponse> update(String id, String entity) {

        return coupaConnection.sendAsyncRequest(getPath() + "/" + id, entity, HttpConstants.Method.PUT);

    }

    public CompletableFuture<HttpResponse> updateWithSubPath(String rootId, String childId, String entity) {

        return coupaConnection.sendAsyncRequest(getPath() + "/" + rootId + "/" + getSubPath() + "/" + childId, entity, HttpConstants.Method.PUT);

    }

    public CompletableFuture<HttpResponse> query(String query, List<CoupaOperatorAttribute> params) {

        String finalQuery = null;

        if (query != null && !query.isEmpty()) {
            finalQuery = (query.startsWith("?")) ? query : "?" + query;
            return coupaConnection.sendAsyncRequest(HttpEncoderDecoderUtils.encodeSpaces(getPath() + finalQuery), HttpConstants.Method.GET);
        }

        if (params == null || params.isEmpty()) {
            return coupaConnection.sendAsyncRequest(getPath(), HttpConstants.Method.GET);
        }

        else {
            Map<String, String> parameterMap = new HashMap<>();
            params.forEach(param -> {
                    String paramName = (param.getOperator().equals(CoupaOperator.eq))?param.getField():param.getField()+"["+param.getOperator().toString()+"]";
                    parameterMap.put(paramName, param.getValue());
                });
            return coupaConnection.sendAsyncRequest(getPath() + "?" + HttpEncoderDecoderUtils.encodeQueryString(parameterMap), HttpConstants.Method.GET);
        }

    }

    public HttpResponse querySync(String id, String query, List<CoupaOperatorAttribute> params, long offset) throws IOException, TimeoutException {

        String fullUrl = null;
        String path = (id == null) ? getPath() : getPath() + "/" + id + "/" + getSubPath();

        if (query != null && !query.isEmpty()) {
            String finalQuery = (query.startsWith("?")) ? query : "?" + query;
            finalQuery = finalQuery + "&offset=" + offset;
            fullUrl = HttpEncoderDecoderUtils.encodeSpaces(path + finalQuery);
        } else if (params == null || params.isEmpty()) {
            fullUrl = path + "?offset=" + offset;
        } else {
            Map<String, String> parameterMap = new HashMap<>();
            params.forEach(param -> {
                String paramName = (param.getOperator().equals(CoupaOperator.eq))?param.getField():param.getField()+"["+param.getOperator().toString()+"]";
                parameterMap.put(paramName, param.getValue());
            });
            parameterMap.put("offset", Long.toString(offset));
            fullUrl = path + "?" + HttpEncoderDecoderUtils.encodeQueryString(parameterMap);
        }

        return coupaConnection.sendSyncRequest(fullUrl, HttpConstants.Method.GET);

    }

    public CompletableFuture<HttpResponse> queryWithSubPath(String id, String query, List<CoupaOperatorAttribute> params) {

        String finalQuery = null;

        if (query != null && !query.isEmpty()) {
            finalQuery = (query.startsWith("?")) ? query : "?" + query;
            return coupaConnection.sendAsyncRequest(HttpEncoderDecoderUtils.encodeSpaces(getPath() + "/" + id + "/" + getSubPath() + finalQuery), HttpConstants.Method.GET);
        }

        if (params == null || params.isEmpty()) {
            return coupaConnection.sendAsyncRequest(getPath() + "/" + id + "/" + getSubPath(), HttpConstants.Method.GET);
        }

        else {
            Map<String, String> parameterMap = new HashMap<>();
            params.forEach(param -> {
                String paramName = (param.getOperator().equals(CoupaOperator.eq))?param.getField():param.getField()+"["+param.getOperator().toString()+"]";
                parameterMap.put(paramName, param.getValue());
            });
            return coupaConnection.sendAsyncRequest(getPath() + "/" + id + "/" + getSubPath() + HttpEncoderDecoderUtils.encodeQueryString(parameterMap), HttpConstants.Method.GET);
        }

    }

    public CompletableFuture<HttpResponse> putSpecialAction(String action, String value, String id) {
        MultiMap<String, String> parameterMap = new MultiMap<>();
        parameterMap.put(action, value);
        return coupaConnection.sendAsyncRequest(getPath() + "/" + id, parameterMap, HttpConstants.Method.PUT);
    }

    public CompletableFuture<HttpResponse> putSpecialActionWithBody(String action, String body, String id) {
        return coupaConnection.sendAsyncRequest(getPath() + "/" + id + "/" + action, body, HttpConstants.Method.PUT);
    }

    public CompletableFuture<HttpResponse> uploadFile(String id, String action, TypedValue<InputStream> inputStream) {
        return coupaConnection.sendAsyncRequest(getPath() + "/" + id + "/" + action, inputStream, HttpConstants.Method.PUT);
    }

    public CompletableFuture<HttpResponse> downloadFile(String id, String action) {
        return coupaConnection.sendAsyncRequest(getPath() + "/" + id + "/" + action, HttpConstants.Method.GET);
    }

}
