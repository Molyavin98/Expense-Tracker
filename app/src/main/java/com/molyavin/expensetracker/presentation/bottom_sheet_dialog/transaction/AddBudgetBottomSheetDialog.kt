package com.molyavin.expensetracker.presentation.bottom_sheet_dialog.transaction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import co.yml.charts.common.extensions.isNotNull
import com.molyavin.expensetracker.R
import com.molyavin.expensetracker.design_system.AppTheme
import com.molyavin.expensetracker.design_system.ButtonTransparent
import com.molyavin.expensetracker.design_system.CustomTextField
import com.molyavin.expensetracker.design_system.Spacing
import com.molyavin.expensetracker.di.scope.Injector
import com.molyavin.expensetracker.presentation.ObserveLifecycleEvents


private val viewModel: AddBudgetBottomSheetViewModel =
    Injector.INSTANCE.provideAddBudgetViewModel()

@Composable
fun AddBudgetBottomSheetDialog(onClick: () -> Unit) {
    viewModel.ObserveLifecycleEvents(LocalLifecycleOwner.current.lifecycle)
    Column(modifier = Modifier.padding(Spacing.M)) {
        val budget by viewModel.budget.collectAsState()
        val currency by viewModel.currency.collectAsState()

        Row(
            modifier = Modifier.padding(start = Spacing.M),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .clip(AppTheme.shapes.medium)
                    .background(AppTheme.colors.highlight.mediumSeaGreen)
                    .padding(horizontal = Spacing.SM, vertical = Spacing.S),
                text = currency,
                color = Color.White,
                style = AppTheme.typography.s3
            )

            CustomTextField(
                value = budget,
                onValueChange = viewModel::setBudget,
                placeholder = stringResource(id = R.string.text_text_field_amount),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
        }

        ButtonTransparent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Spacing.S, vertical = Spacing.M),
            onClick = {
                if (budget.isNotNull()) {
                    viewModel.addBudget()
                    onClick()
                }
            },
            text = "Add Balance"
        )
    }
}