/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.internal.config;

import org.mule.extension.coupa.internal.connection.provider.CoupaConnectionProvider;
import org.mule.extension.coupa.internal.utils.Constants;
import org.mule.extension.coupa.internal.operation.*;
import org.mule.runtime.extension.api.annotation.Configuration;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;
import org.mule.runtime.extension.api.annotation.param.display.Placement;

import static org.mule.runtime.api.meta.ExpressionSupport.NOT_SUPPORTED;


@Configuration(name = "config")
@ConnectionProviders({CoupaConnectionProvider.class})
@Operations({BasicApiOperations.class, ApprovalsApiOperations.class, ExpenseReportsApiOperations.class,
        InventoryTransactionsApiOperations.class, InvoicesApiOperations.class, PurchaseOrdersApiOperations.class,
        RequisitionsApiOperations.class, SuppliersApiOperations.class})
public class CoupaConf {

    /**
     * Invoices custom fields
     *
     * Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user
     * Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary
     *
     */
    @Parameter
    @Optional
    @Expression(NOT_SUPPORTED)
    @Placement(tab = "Custom Metadata", order = 0)
    @DisplayName("Invoice custom fields")
    @Example("Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary")
    private String invoiceCustomFields;

    /**
     * Invoice lines custom fields
     *
     * Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user
     * Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary
     *
     */
    @Parameter
    @Optional
    @Expression(NOT_SUPPORTED)
    @Placement(tab = "Custom Metadata", order = 1)
    @DisplayName("Invoice lines custom fields")
    @Example("Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user")
    private String invoiceLinesCustomFields;

    /**
     * Invoice charges custom fields
     *
     * Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user
     * Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary
     *
     */
    @Parameter
    @Optional
    @Expression(NOT_SUPPORTED)
    @Placement(tab = "Custom Metadata", order = 2)
    @DisplayName("Invoice charges custom fields")
    @Example("Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary")
    private String invoiceChargesCustomFields;

    /**
     * Purchase orders custom fields
     *
     * Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user
     * Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary
     *
     */
    @Parameter
    @Optional
    @Expression(NOT_SUPPORTED)
    @Placement(tab = "Custom Metadata", order = 3)
    @DisplayName("Purchase orders custom fields")
    @Example("Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user")
    private String purchaseOrdersCustomFields;

    /**
     * Purchase order lines custom fields
     *
     * Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user
     * Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary
     *
     */
    @Parameter
    @Optional
    @Expression(NOT_SUPPORTED)
    @Placement(tab = "Custom Metadata", order = 4)
    @DisplayName("Purchase order lines custom fields")
    @Example("Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary")
    private String purchaseOrderLinesCustomFields;

    /**
     * Inventory transactions custom fields
     *
     * Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user
     * Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary
     *
     */
    @Parameter
    @Optional
    @Expression(NOT_SUPPORTED)
    @Placement(tab = "Custom Metadata", order = 5)
    @DisplayName("Inventory transactions custom fields")
    @Example("Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user")
    private String inventoryTransactionsCustomFields;

    /**
     * Suppliers custom fields
     *
     * Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user
     * Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary
     *
     */
    @Parameter
    @Optional
    @Expression(NOT_SUPPORTED)
    @Placement(tab = "Custom Metadata", order = 6)
    @DisplayName("Suppliers custom fields")
    @Example("Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary")
    private String suppliersCustomFields;

    /**
     * Supplier sites custom fields
     *
     * Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user
     * Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary
     *
     */
    @Parameter
    @Optional
    @Expression(NOT_SUPPORTED)
    @Placement(tab = "Custom Metadata", order = 7)
    @DisplayName("Supplier sites custom fields")
    @Example("Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user")
    private String supplierSitesCustomFields;

    /**
     * Expense reports custom fields
     *
     * Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user
     * Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary
     *
     */
    @Parameter
    @Optional
    @Expression(NOT_SUPPORTED)
    @Placement(tab = "Custom Metadata", order = 8)
    @DisplayName("Expense reports custom fields")
    @Example("Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary")
    private String expenseReportsCustomFields;

    /**
     * Expense report lines custom fields
     *
     * Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user
     * Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary
     *
     */
    @Parameter
    @Optional
    @Expression(NOT_SUPPORTED)
    @Placement(tab = "Custom Metadata", order = 9)
    @DisplayName("Expense report lines custom fields")
    @Example("Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user")
    private String expenseReportLinesCustomFields;

    /**
     * Requisitions custom fields
     *
     * Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user
     * Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary
     *
     */
    @Parameter
    @Optional
    @Expression(NOT_SUPPORTED)
    @Placement(tab = "Custom Metadata", order = 10)
    @DisplayName("Requisitions custom fields")
    @Example("Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary")
    private String requisitionsCustomFields;

    /**
     * Requisition lines custom fields
     *
     * Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user
     * Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary
     *
     */
    @Parameter
    @Optional
    @Expression(NOT_SUPPORTED)
    @Placement(tab = "Custom Metadata", order = 11)
    @DisplayName("Requisition lines custom fields")
    @Example("Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user")
    private String requisitionLinesCustomFields;

    /**
     * Approvals custom fields
     *
     * Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user
     * Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary
     *
     */
    @Parameter
    @Optional
    @Expression(NOT_SUPPORTED)
    @Placement(tab = "Custom Metadata", order = 12)
    @DisplayName("Approvals custom fields")
    @Example("Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary")
    private String approvalsCustomFields;

    /**
     * Users custom fields
     *
     * Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user
     * Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary
     *
     */
    @Parameter
    @Optional
    @Expression(NOT_SUPPORTED)
    @Placement(tab = "Custom Metadata", order = 13)
    @DisplayName("Users custom fields")
    @Example("Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user")
    private String usersCustomFields;

    /**
     * Lookup values custom fields
     *
     * Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user
     * Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary
     *
     */
    @Parameter
    @Optional
    @Expression(NOT_SUPPORTED)
    @Placement(tab = "Custom Metadata", order = 14)
    @DisplayName("Lookup values custom fields")
    @Example("Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary")
    private String lookupValuesCustomFields;

    /**
     * Addresses custom fields
     *
     * Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user
     * Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary
     *
     */
    @Parameter
    @Optional
    @Expression(NOT_SUPPORTED)
    @Placement(tab = "Custom Metadata", order = 15)
    @DisplayName("Addresses custom fields")
    @Example("Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user")
    private String addressesCustomFields;

    /**
     * Commodities custom fields
     *
     * Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user
     * Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary
     *
     */
    @Parameter
    @Optional
    @Expression(NOT_SUPPORTED)
    @Placement(tab = "Custom Metadata", order = 16)
    @DisplayName("Commodities custom fields")
    @Example("Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary")
    private String commoditiesCustomFields;

    /**
     * Departments custom fields
     *
     * Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user
     * Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary
     *
     */
    @Parameter
    @Optional
    @Expression(NOT_SUPPORTED)
    @Placement(tab = "Custom Metadata", order = 17)
    @DisplayName("Departments custom fields")
    @Example("Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user")
    private String departmentsCustomFields;

    /**
     * Items custom fields
     *
     * Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user
     * Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary
     *
     */
    @Parameter
    @Optional
    @Expression(NOT_SUPPORTED)
    @Placement(tab = "Custom Metadata", order = 18)
    @DisplayName("Items custom fields")
    @Example("Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary")
    private String itemsCustomFields;

    /**
     * Contracts custom fields
     *
     * Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user
     * Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary
     *
     */
    @Parameter
    @Optional
    @Expression(NOT_SUPPORTED)
    @Placement(tab = "Custom Metadata", order = 19)
    @DisplayName("Contracts custom fields")
    @Example("Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user")
    private String contractsCustomFields;

    /**
     * Asset Tags custom fields
     *
     * Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user
     * Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary
     *
     */
    @Parameter
    @Optional
    @Expression(NOT_SUPPORTED)
    @Placement(tab = "Custom Metadata", order = 20)
    @DisplayName("Asset Tags custom fields")
    @Example("Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary")
    private String assetTagsCustomFields;

    /**
     * Warehouses custom fields
     *
     * Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user
     * Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary
     *
     */
    @Parameter
    @Optional
    @Expression(NOT_SUPPORTED)
    @Placement(tab = "Custom Metadata", order = 21)
    @DisplayName("Warehouses custom fields")
    @Example("Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user")
    private String warehousesCustomFields;

    /**
     * Forms custom fields
     *
     * Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user
     * Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary
     *
     */
    @Parameter
    @Optional
    @Expression(NOT_SUPPORTED)
    @Placement(tab = "Custom Metadata", order = 22)
    @DisplayName("Forms custom fields")
    @Example("Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary")
    private String formsCustomFields;

    /**
     * Punchout sites custom fields
     *
     * Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user
     * Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary
     *
     */
    @Parameter
    @Optional
    @Expression(NOT_SUPPORTED)
    @Placement(tab = "Custom Metadata", order = 23)
    @DisplayName("Punchout sites custom fields")
    @Example("Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user")
    private String punchoutSitesCustomFields;

    /**
     * Expense categories custom fields
     *
     * Example: field-one,String; field-two,Integer; field-date,Date_Time; field-ref,Reference:user
     * Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary
     *
     */
    @Parameter
    @Optional
    @Expression(NOT_SUPPORTED)
    @Placement(tab = "Custom Metadata", order = 24)
    @DisplayName("Expense categories custom fields")
    @Example("Available types: String, Integer, Decimal, Date_Time, Reference:user, Reference:lookup-value, Reference:user-group-summary")
    private String expenseCategoriesCustomFields;

    public String getCustomFieldsStringByKey(String key) {

        switch (key) {
            case Constants.INVOICE_KEY:
            case Constants.INVOICE:
                return invoiceCustomFields;
            case "invoice-line-summary":
                return invoiceLinesCustomFields;
            case "invoice-charge-summary":
                return invoiceChargesCustomFields;
            case Constants.PURCHASE_ORDER_KEY:
            case Constants.PURCHASE_ORDER:
                return purchaseOrdersCustomFields;
            case "order-line-summary":
                return purchaseOrderLinesCustomFields;
            case Constants.INVENTORY_TRANSACTION_KEY:
            case Constants.INVENTORY_TRANSACTION:
                return inventoryTransactionsCustomFields;
            case Constants.SUPPLIER_KEY:
            case Constants.SUPPLIER:
                return suppliersCustomFields;
            case "supplier-site-summary":
                return supplierSitesCustomFields;
            case Constants.EXPENSE_REPORT_KEY:
            case Constants.EXPENSE_REPORT:
                return expenseReportsCustomFields;
            case "expense-line-summary":
                return expenseReportLinesCustomFields;
            case Constants.REQUISITION_KEY:
            case Constants.REQUISITION:
                return requisitionsCustomFields;
            case "requisition-line-summary":
                return requisitionLinesCustomFields;
            case Constants.APPROVAL_KEY:
            case Constants.APPROVAL:
                return approvalsCustomFields;
            case Constants.USER_KEY:
            case Constants.USER:
                return usersCustomFields;
            case Constants.LOOKUP_VALUE_KEY:
            case Constants.LOOKUP_VALUE:
                return lookupValuesCustomFields;
            case Constants.ADDRESS_KEY:
            case Constants.ADDRESS:
                return addressesCustomFields;
            case "commodity-summary":
                return commoditiesCustomFields;
            case "department-summary":
                return departmentsCustomFields;
            case "item-summary":
                return itemsCustomFields;
            case "contract-summary":
                return contractsCustomFields;
            case "asset-tag-summary":
                return assetTagsCustomFields;
            case "warehouse-summary":
                return warehousesCustomFields;
            case "form-response-summary":
                return formsCustomFields;
            case "punchout-site-summary":
                return punchoutSitesCustomFields;
            case "expense-category-summary":
                return expenseCategoriesCustomFields;
            default:
                return null;
        }

    }

}
