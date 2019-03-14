/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.automation.unit;

import org.junit.Test;
import org.mule.extension.coupa.internal.CoupaConstants;
import org.mule.extension.coupa.internal.utils.Constants;

import static org.junit.Assert.assertEquals;

public class CoupaConstantsTest {

    @Test
    public void testCoupaConstants() {
        assertEquals("coupa-connection", CoupaConstants.COUPA_CONNECTION_NAME);
    }

    @Test
    public void testConstants() {
        assertEquals("invoice", Constants.INVOICE_KEY);
        assertEquals("purchase-order", Constants.PURCHASE_ORDER_KEY);
        assertEquals("inventory-transaction", Constants.INVENTORY_TRANSACTION_KEY);
        assertEquals("requisition", Constants.REQUISITION_KEY);
        assertEquals("supplier", Constants.SUPPLIER_KEY);
        assertEquals("lookup-value", Constants.LOOKUP_VALUE_KEY);
        assertEquals("address", Constants.ADDRESS_KEY);
        assertEquals("approval", Constants.APPROVAL_KEY);
        assertEquals("expense-report", Constants.EXPENSE_REPORT_KEY);
        assertEquals("user", Constants.USER_KEY);
        assertEquals("remit-to-address", Constants.REMIT_TO_ADDRESS_KEY);
        assertEquals("approve", Constants.APPROVAL_APPROVE_KEY);
        assertEquals("hold", Constants.APPROVAL_HOLD_KEY);
        assertEquals("reject", Constants.APPROVAL_REJECT_KEY);
        assertEquals("submit", Constants.EXPENSE_REPORT_SUBMIT_KEY);
        assertEquals("void", Constants.INVENTORY_TRANSACTION_VOID_KEY);
        assertEquals("void", Constants.INVOICE_VOID_KEY);
        assertEquals("image-scan", Constants.INVOICE_UPLOAD_IMAGE_KEY);
        assertEquals("retrieve_image_scan", Constants.INVOICE_DOWNLOAD_IMAGE_KEY);
        assertEquals("cancel", Constants.PURCHASE_ORDER_CANCEL_KEY);
        assertEquals("close", Constants.PURCHASE_ORDER_CLOSE_KEY);
        assertEquals("issue", Constants.PURCHASE_ORDER_ISSUE_KEY);
        assertEquals("issue_without_send", Constants.PURCHASE_ORDER_ISSUE_NO_SEND_KEY);
        assertEquals("release_from_buyer_hold", Constants.PURCHASE_ORDER_RELEASE_KEY);
        assertEquals("create_as_cart", Constants.REQUISITION_CART_KEY);
        assertEquals("submit_for_approval", Constants.REQUISITION_APPROVAL_KEY);
    }
}
