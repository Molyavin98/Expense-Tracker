package com.molyavin.expensetracker.presentation.screen.transaction

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.molyavin.expensetracker.R
import com.molyavin.expensetracker.design_system.ButtonClose
import com.molyavin.expensetracker.design_system.ButtonTransparent
import com.molyavin.expensetracker.design_system.DefaultRadioButton
import com.molyavin.expensetracker.design_system.DefaultTextField
import com.molyavin.expensetracker.design_system.Spacing
import com.molyavin.expensetracker.di.scope.Injector
import com.molyavin.expensetracker.presentation.screen.BaseActivity
import com.molyavin.expensetracker.presentation.viewmodel.add_transaction.AddTransactionViewModel

class AddTransactionScreen : BaseActivity() {

    override val viewModel: AddTransactionViewModel =
        Injector.INSTANCE.provideAddTransactionViewModel()

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier.padding(Spacing.M)
        ) {
            ButtonClose(onClick = {})

            val label by viewModel.label.collectAsState()
            val amount by viewModel.amount.collectAsState()


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
                onValueChange = { viewModel.setAmount(it) },
                label = stringResource(id = R.string.text_text_field_amount),
                hint = stringResource(id = R.string.text_text_field_amount_hint),
            )


            val selectedOption = remember { mutableStateOf(true) }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Spacing.S)
            ) {
                DefaultRadioButton(
                    modifier = Modifier.weight(1f),
                    onClick = { selectedOption.value = !selectedOption.value },
                    isSelected = selectedOption.value,
                    text = "Income"
                )
                DefaultRadioButton(
                    modifier = Modifier.weight(1f),
                    onClick = { selectedOption.value = !selectedOption.value },
                    isSelected = !selectedOption.value,
                    text = "Expense"
                )
            }

            ButtonTransparent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = Spacing.S, end = Spacing.S),
                onClick = {},
                text = "Add transaction"
            )

        }
    }
}