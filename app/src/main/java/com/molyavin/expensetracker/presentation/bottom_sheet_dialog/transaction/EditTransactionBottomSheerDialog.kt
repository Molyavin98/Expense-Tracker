package com.molyavin.expensetracker.presentation.bottom_sheet_dialog.transaction

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import co.yml.charts.common.extensions.isNotNull
import com.molyavin.expensetracker.R
import com.molyavin.expensetracker.design_system.ButtonTransparent
import com.molyavin.expensetracker.design_system.DefaultTextField
import com.molyavin.expensetracker.design_system.Spacing
import com.molyavin.expensetracker.di.scope.Injector
import com.molyavin.expensetracker.presentation.ObserveLifecycleEvents

private val viewModel: EditTransactionBottomSheetViewModel =
    Injector.INSTANCE.provideEditTransactionViewModel()

@Composable
fun EditTransactionBottomSheetDialog(id: String, onClickSave: () -> Unit) {
    viewModel.ObserveLifecycleEvents(LocalLifecycleOwner.current.lifecycle)
    Column(
        modifier = Modifier.padding(Spacing.M)
    ) {
        viewModel.setId(id)
        val label by viewModel.label.collectAsState()
        val amount by viewModel.amount.collectAsState()

        DefaultTextField(
            modifier = Modifier
                .padding(vertical = Spacing.XS)
                .weight(1f),
            value = label,
            onValueChange = { viewModel.setLabel(it) },
            label = stringResource(id = R.string.text_text_field_label),
            hint = stringResource(id = R.string.text_text_field_label_hint),
        )

        DefaultTextField(
            modifier = Modifier
                .padding(vertical = Spacing.XS)
                .weight(1f),
            value = amount,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { viewModel.setAmount(it) },
            label = stringResource(id = R.string.text_text_field_amount),
            hint = stringResource(id = R.string.text_text_field_amount_hint),
        )

        ButtonTransparent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Spacing.S, top = Spacing.M, end = Spacing.S),
            onClick = {
                if (label.isNotNull() && amount.isNotNull()) {
                    viewModel.editTransaction()
                    onClickSave()
                }
            },
            text = "Add transaction"
        )
    }
}