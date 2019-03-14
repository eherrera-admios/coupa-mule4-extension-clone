/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.automation.unit;

import org.junit.Test;
import org.mule.extension.coupa.internal.config.CoupaConf;
import org.mule.extension.coupa.internal.utils.Constants;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CoupaConfTest {

    @Test
    public void testGetCustomFieldsStringByKey() throws Exception {
        CoupaConf conf = new CoupaConf();


        Field fields[] = conf.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println("Field Name: " + field.getName());
            if (field.isSynthetic() || field.getName().startsWith("$")) { continue; } // to avoid jacoco injected synthetics
            field.setAccessible(true);
            field.set(conf, field.getName());
        }

        assertEquals("invoiceCustomFields", conf.getCustomFieldsStringByKey(Constants.INVOICE_KEY));
        assertEquals("invoiceCustomFields", conf.getCustomFieldsStringByKey(Constants.INVOICE));
        assertEquals("invoiceLinesCustomFields", conf.getCustomFieldsStringByKey("invoice-line-summary"));
        assertEquals("invoiceChargesCustomFields", conf.getCustomFieldsStringByKey("invoice-charge-summary"));
        assertEquals("purchaseOrdersCustomFields", conf.getCustomFieldsStringByKey(Constants.PURCHASE_ORDER_KEY));
        assertEquals("purchaseOrdersCustomFields", conf.getCustomFieldsStringByKey(Constants.PURCHASE_ORDER));
        assertEquals("purchaseOrderLinesCustomFields", conf.getCustomFieldsStringByKey("order-line-summary"));
        assertEquals("inventoryTransactionsCustomFields", conf.getCustomFieldsStringByKey(Constants.INVENTORY_TRANSACTION_KEY));
        assertEquals("inventoryTransactionsCustomFields", conf.getCustomFieldsStringByKey(Constants.INVENTORY_TRANSACTION));
        assertEquals("suppliersCustomFields", conf.getCustomFieldsStringByKey(Constants.SUPPLIER_KEY));
        assertEquals("suppliersCustomFields", conf.getCustomFieldsStringByKey(Constants.SUPPLIER));
        assertEquals("supplierSitesCustomFields", conf.getCustomFieldsStringByKey("supplier-site-summary"));
        assertEquals("expenseReportsCustomFields", conf.getCustomFieldsStringByKey(Constants.EXPENSE_REPORT_KEY));
        assertEquals("expenseReportsCustomFields", conf.getCustomFieldsStringByKey(Constants.EXPENSE_REPORT));
        assertEquals("expenseReportLinesCustomFields", conf.getCustomFieldsStringByKey("expense-line-summary"));
        assertEquals("requisitionsCustomFields", conf.getCustomFieldsStringByKey(Constants.REQUISITION_KEY));
        assertEquals("requisitionsCustomFields", conf.getCustomFieldsStringByKey(Constants.REQUISITION));
        assertEquals("requisitionLinesCustomFields", conf.getCustomFieldsStringByKey("requisition-line-summary"));
        assertEquals("approvalsCustomFields", conf.getCustomFieldsStringByKey(Constants.APPROVAL_KEY));
        assertEquals("approvalsCustomFields", conf.getCustomFieldsStringByKey(Constants.APPROVAL));
        assertEquals("usersCustomFields", conf.getCustomFieldsStringByKey(Constants.USER_KEY));
        assertEquals("usersCustomFields", conf.getCustomFieldsStringByKey(Constants.USER));
        assertEquals("lookupValuesCustomFields", conf.getCustomFieldsStringByKey(Constants.LOOKUP_VALUE_KEY));
        assertEquals("lookupValuesCustomFields", conf.getCustomFieldsStringByKey(Constants.LOOKUP_VALUE));
        assertEquals("addressesCustomFields", conf.getCustomFieldsStringByKey(Constants.ADDRESS_KEY));
        assertEquals("addressesCustomFields", conf.getCustomFieldsStringByKey(Constants.ADDRESS));
        assertEquals("commoditiesCustomFields", conf.getCustomFieldsStringByKey("commodity-summary"));
        assertEquals("departmentsCustomFields", conf.getCustomFieldsStringByKey("department-summary"));
        assertEquals("itemsCustomFields", conf.getCustomFieldsStringByKey("item-summary"));
        assertEquals("contractsCustomFields", conf.getCustomFieldsStringByKey("contract-summary"));
        assertEquals("assetTagsCustomFields", conf.getCustomFieldsStringByKey("asset-tag-summary"));
        assertEquals("warehousesCustomFields", conf.getCustomFieldsStringByKey("warehouse-summary"));
        assertEquals("formsCustomFields", conf.getCustomFieldsStringByKey("form-response-summary"));
        assertEquals("punchoutSitesCustomFields", conf.getCustomFieldsStringByKey("punchout-site-summary"));
        assertEquals("expenseCategoriesCustomFields", conf.getCustomFieldsStringByKey("expense-category-summary"));
        assertNull(conf.getCustomFieldsStringByKey(""));
    }

}
