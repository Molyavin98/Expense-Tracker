package com.molyavin.expensetracker.presentation.bottom_sheet_dialog.currency

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import com.molyavin.expensetracker.R
import com.molyavin.expensetracker.design_system.AppTheme
import com.molyavin.expensetracker.design_system.DefaultButton
import com.molyavin.expensetracker.design_system.Divider
import com.molyavin.expensetracker.design_system.Spacing
import com.molyavin.expensetracker.di.scope.Injector
import com.molyavin.expensetracker.presentation.ObserveLifecycleEvents
import com.molyavin.expensetracker.presentation.screen.setting.Currency

private val viewModel: CurrencyBottomSheetViewModel =
    Injector.INSTANCE.provideCurrencyBottomSheetViewModel()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyBottomSheetDialog(onClickSave: (String) -> Unit, onDismiss: () -> Unit) {
    viewModel.ObserveLifecycleEvents(LocalLifecycleOwner.current.lifecycle)
    val modalBottomSheetState = rememberModalBottomSheetState()
    val currency by viewModel.currency.collectAsState()

    ModalBottomSheet(
        containerColor = AppTheme.colors.onBackground.modal,
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        onDismissRequest = onDismiss
    ) {
        Column(Modifier.selectableGroup()) {
            val countryCurrency = listOf(
                Currency.USD_WITH_FLAG,
                Currency.EUR_WITH_FLAG,
                Currency.UAH_WITH_FLAG
            )

            val (selectedOption, onOptionSelected) = remember { mutableStateOf(currency) }

            countryCurrency.forEach { text ->
                CurrencyItem(
                    selected = selectedOption == text,
                    text = text,
                    onClick = { onOptionSelected(text) }
                )
                Divider()
            }

            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Spacing.M)
                    .align(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.text_save),
                onClick = {
                    onClickSave(selectedOption)
                    viewModel.saveCurrency(
                        selectedOption
                    )
                }
            )
        }
    }
}

@Composable
private fun CurrencyItem(
    selected: Boolean,
    onClick: () -> Unit,
    text: String
) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
            .padding(horizontal = Spacing.M, vertical = Spacing.S),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = text,
            color = AppTheme.colors.onBackground.primary,
            style = AppTheme.typography.s3
        )
        RadioButton(
            modifier = Modifier,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = AppTheme.colors.onBackground.primary,
            ),
            selected = selected,
        )
    }
}



