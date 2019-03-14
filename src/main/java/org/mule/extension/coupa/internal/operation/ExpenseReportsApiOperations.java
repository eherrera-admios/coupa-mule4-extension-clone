/**
 * Copyright 2018 Coupa Software Inc.
 *
 * Coupa grants to you a royalty-free, non-transferable, non-sublicensable, and non-exclusive license to use the software for your own internal business operations during the subscription term (“License”).
 *
 * Unless required by applicable law or agreed otherwise between Coupa and you, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License.md file for the specific language governing permissions and limitations under the License.
 */
package org.mule.extension.coupa.internal.operation;

import org.mule.extension.coupa.internal.CoupaError;
import org.mule.extension.coupa.internal.connection.CoupaConnection;
import org.mule.extension.coupa.internal.service.ExpenseReportsMetadataResolver;
import org.mule.runtime.extension.api.annotation.metadata.OutputResolver;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Summary;
import org.mule.runtime.extension.api.runtime.process.CompletionCallback;

import java.io.InputStream;

import static org.mule.extension.coupa.internal.utils.Constants.EXPENSE_REPORT_SUBMIT_KEY;
import static org.mule.runtime.extension.api.annotation.param.MediaType.APPLICATION_JSON;

public class ExpenseReportsApiOperations extends CoupaOperations {

    @MediaType(value = APPLICATION_JSON)
    @DisplayName("Expense Reports | Submit")
    @OutputResolver(output = ExpenseReportsMetadataResolver.class)
    public void expenseReports(@Connection CoupaConnection connection,
                               @DisplayName("ID") @Summary("Enter ID") String id,
                               CompletionCallback<InputStream, Void> callback) {

        connection.getExpenseReports()
                .putSpecialActionWithBody(EXPENSE_REPORT_SUBMIT_KEY, "{}", id)
                .whenCompleteAsync(new CoupaResponseConsumer(CoupaError.EXECUTION, callback));

    }

}
