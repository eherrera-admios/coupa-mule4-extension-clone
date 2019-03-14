/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.internal.utils;

public class Constants {

    private Constants(){}

    public static final String INVOICE = "Invoice";
    public static final String PURCHASE_ORDER = "Purchase Order";
    public static final String INVENTORY_TRANSACTION = "Inventory Transaction";
    public static final String REQUISITION = "Requisition";
    public static final String SUPPLIER = "Supplier";
    public static final String LOOKUP_VALUE = "Lookup value";
    public static final String ADDRESS = "Address";
    public static final String APPROVAL = "Approval";
    public static final String EXPENSE_REPORT = "Expense Report";
    public static final String USER = "User";

    public static final String APPROVAL_APPROVE = "Approve";
    public static final String APPROVAL_HOLD = "Hold";
    public static final String APPROVAL_REJECT = "Reject";

    public static final String INVOICE_VOID = "Void";
    public static final String INVOICE_UPLOAD_IMAGE = "Upload Image";
    public static final String INVOICE_DOWNLOAD_IMAGE = "Download Image";

    public static final String PURCHASE_ORDER_CANCEL = "Cancel";
    public static final String PURCHASE_ORDER_CLOSE = "Close";
    public static final String PURCHASE_ORDER_ISSUE = "Issue With Send";
    public static final String PURCHASE_ORDER_ISSUE_NO_SEND = "Issue Without Send";
    public static final String PURCHASE_ORDER_RELEASE = "Release PO from buyer hold";

    public static final String REQUISITION_CART = "Create in draft status";
    public static final String REQUISITION_APPROVAL = "Create and submit for approval";


    // KEYS


    public static final String INVOICE_KEY = "invoice";
    public static final String PURCHASE_ORDER_KEY = "purchase-order";
    public static final String INVENTORY_TRANSACTION_KEY = "inventory-transaction";
    public static final String REQUISITION_KEY = "requisition";
    public static final String SUPPLIER_KEY = "supplier";
    public static final String LOOKUP_VALUE_KEY = "lookup-value";
    public static final String ADDRESS_KEY = "address";
    public static final String APPROVAL_KEY = "approval";
    public static final String EXPENSE_REPORT_KEY = "expense-report";
    public static final String USER_KEY = "user";
    public static final String REMIT_TO_ADDRESS_KEY = "remit-to-address";

    public static final String APPROVAL_APPROVE_KEY = "approve";
    public static final String APPROVAL_HOLD_KEY = "hold";
    public static final String APPROVAL_REJECT_KEY = "reject";

    public static final String EXPENSE_REPORT_SUBMIT_KEY = "submit";
    public static final String INVENTORY_TRANSACTION_VOID_KEY = "void";

    public static final String INVOICE_VOID_KEY = "void";
    public static final String INVOICE_UPLOAD_IMAGE_KEY = "image-scan";
    public static final String INVOICE_DOWNLOAD_IMAGE_KEY = "retrieve_image_scan";

    public static final String PURCHASE_ORDER_CANCEL_KEY = "cancel";
    public static final String PURCHASE_ORDER_CLOSE_KEY = "close";
    public static final String PURCHASE_ORDER_ISSUE_KEY = "issue";
    public static final String PURCHASE_ORDER_ISSUE_NO_SEND_KEY = "issue_without_send";
    public static final String PURCHASE_ORDER_RELEASE_KEY = "release_from_buyer_hold";


    public static final String REQUISITION_CART_KEY = "create_as_cart";
    public static final String REQUISITION_APPROVAL_KEY = "submit_for_approval";

}
