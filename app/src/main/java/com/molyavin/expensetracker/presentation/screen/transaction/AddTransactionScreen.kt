package com.molyavin.expensetracker.presentation.screen.transaction

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import co.yml.charts.common.extensions.isNotNull
import com.molyavin.expensetracker.R
import com.molyavin.expensetracker.design_system.ButtonClose
import com.molyavin.expensetracker.design_system.ButtonTransparent
import com.molyavin.expensetracker.design_system.DefaultRadioButton
import com.molyavin.expensetracker.design_system.DefaultTextField
import com.molyavin.expensetracker.design_system.Spacing
import com.molyavin.expensetracker.di.scope.Injector
import com.molyavin.expensetracker.presentation.BaseScreen
import com.molyavin.expensetracker.utils.DateTimeFormatter
import java.util.Calendar

class AddTransactionScreen : BaseScreen() {

    override val viewModel: AddTransactionViewModel =
        Injector.INSTANCE.provideAddTransactionViewModel()

    @SuppressLint("StateFlowValueCalledInComposition")
    @Composable
    override fun Content() {
        Column(
            modifier = Modifier.padding(Spacing.M)
        ) {
            ButtonClose(onClick = viewModel::navigateBack)

            val label by viewModel.label.collectAsState()
            val amount by viewModel.amount.collectAsState()
            val isIncome by viewModel.isIncome.collectAsState()


            DefaultTextField(
                modifier = Modifier
                    .padding(top = 3.dp, bottom = 3.dp)
                    .weight(50f),
                value = label,
                onValueChange = { viewModel.setLabel(it) },
                label = stringResource(id = R.string.text_text_field_label),
                hint = stringResource(id = R.string.text_text_field_label_hint),
            )

            DefaultTextField(
                modifier = Modifier
                    .padding(top = 3.dp, bottom = 3.dp)
                    .weight(50f),
                value = amount,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = { viewModel.setAmount(it) },
                label = stringResource(id = R.string.text_text_field_amount),
                hint = stringResource(id = R.string.text_text_field_amount_hint),
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Spacing.S)
            ) {
                DefaultRadioButton(
                    modifier = Modifier.weight(1f),
                    onClick = { viewModel.setIsIncome(true) },
                    isSelected = isIncome,
                    text = "Income"
                )
                DefaultRadioButton(
                    modifier = Modifier.weight(1f),
                    onClick = { viewModel.setIsIncome(false) },
                    isSelected = !isIncome,
                    text = "Expense"
                )
            }
            val currentDateTime = Calendar.getInstance()
            val formattedDateTime = DateTimeFormatter.formatDateTime(currentDateTime)

            ButtonTransparent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = Spacing.S, end = Spacing.S),
                onClick = {
                    if (label.isNotNull() && amount.isNotNull()) {
                        viewModel.setId(currentDateTime.timeInMillis)
                        viewModel.addTransactionItem()
                        finish()
                    }

                },
                text = "Add transaction"
            )

        }
    }
}