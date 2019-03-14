/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.internal.connection;

import org.mule.extension.coupa.internal.connection.api.*;
import org.mule.runtime.api.metadata.TypedValue;
import org.mule.runtime.api.util.MultiMap;
import org.mule.runtime.http.api.HttpConstants;
import org.mule.runtime.http.api.HttpService;
import org.mule.runtime.http.api.client.HttpClient;
import org.mule.runtime.http.api.client.HttpClientConfiguration;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

import static org.mule.extension.coupa.internal.CoupaConstants.COUPA_CONNECTION_NAME;

public final class CoupaConnection {

  private final CoupaRequestBuilderFactory requestBuilderFactory;

  private final Invoices invoices;
  private final Users users;
  private final Suppliers suppliers;
  private final Requisitions requisitions;
  private final PurchaseOrders purchaseOrders;
  private final LookupValues lookupValues;
  private final Addresses addresses;
  private final ExpenseReports expenseReports;
  private final InventoryTransactions inventoryTransactions;
  private final Approvals approvals;

  private HttpClient httpClient;

  public CoupaConnection(String instance, String apiKey, HttpService httpService) {

    initHttpClient(httpService);
    requestBuilderFactory = new CoupaRequestBuilderFactory(instance, apiKey, httpClient);

    invoices = new Invoices(this);
    users = new Users(this);
    suppliers = new Suppliers(this);
    requisitions = new Requisitions(this);
    purchaseOrders = new PurchaseOrders(this);
    lookupValues = new LookupValues(this);
    addresses = new Addresses(this);
    expenseReports = new ExpenseReports(this);
    inventoryTransactions = new InventoryTransactions(this);
    approvals = new Approvals(this);

  }

  private void initHttpClient(HttpService httpService) {
    HttpClientConfiguration.Builder builder = new HttpClientConfiguration.Builder();
    builder.setName(COUPA_CONNECTION_NAME);
    httpClient = httpService.getClientFactory().create(builder.build());
    httpClient.start();
  }

  public void invalidate() {
      httpClient.stop();
  }

  public boolean isValid() {

       return users.testConnection();

  }

  public CompletableFuture<HttpResponse> sendAsyncRequest(String path, MultiMap<String, String> parameterMap, HttpConstants.Method method) {

    return requestBuilderFactory.newRequest(path, parameterMap).sendAsyncRequest(method);

  }

  public CompletableFuture<HttpResponse> sendAsyncRequest(String path, HttpConstants.Method method) {

    return requestBuilderFactory.newRequest(path).sendAsyncRequest(method);

  }

  public CompletableFuture<HttpResponse> sendAsyncRequest(String path, String entity, HttpConstants.Method method) {

    return requestBuilderFactory.newRequest(path, entity).sendAsyncRequest(method);

  }

  public CompletableFuture<HttpResponse> sendAsyncRequest(String path, TypedValue<InputStream> inputStream, HttpConstants.Method method) {
    return requestBuilderFactory.newRequest(path, inputStream).sendAsyncRequest(method);
  }

  public HttpResponse sendSyncRequest(String path, MultiMap<String, String> parameterMap, HttpConstants.Method method) throws IOException, TimeoutException {
      return requestBuilderFactory.newRequest(path, parameterMap).sendRequest(method);
  }

  public HttpResponse sendSyncRequest(String path, HttpConstants.Method method) throws IOException, TimeoutException {
      return requestBuilderFactory.newRequest(path).sendRequest(method);
  }

  public Invoices getInvoices() {
    return invoices;
  }

  public Users getUsers() {
    return users;
  }

  public Suppliers getSuppliers() {
    return suppliers;
  }

  public Requisitions getRequisitions() {
    return requisitions;
  }

  public PurchaseOrders getPurchaseOrders() {
    return purchaseOrders;
  }

  public LookupValues getLookupValues() {
    return lookupValues;
  }

  public Addresses getAddresses() {
    return addresses;
  }

  public ExpenseReports getExpenseReports() {
    return expenseReports;
  }

  public InventoryTransactions getInventoryTransactions() {
    return inventoryTransactions;
  }

  public Approvals getApprovals() {
    return approvals;
  }
}
