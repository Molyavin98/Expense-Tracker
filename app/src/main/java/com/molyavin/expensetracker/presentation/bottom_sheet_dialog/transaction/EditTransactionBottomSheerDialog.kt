package com.molyavin.expensetracker.presentation.bottom_sheet_dialog.transaction

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import co.yml.charts.common.extensions.isNotNull
import com.molyavin.expensetracker.R
import com.molyavin.expensetracker.design_system.AppTheme
import com.molyavin.expensetracker.design_system.ButtonTransparent
import com.molyavin.expensetracker.design_system.CustomTextField
import com.molyavin.expensetracker.design_system.IconSize
import com.molyavin.expensetracker.design_system.Spacing
import com.molyavin.expensetracker.design_system.colorBackgroundCategoryImage
import com.molyavin.expensetracker.di.scope.Injector
import com.molyavin.expensetracker.presentation.ObserveLifecycleEvents
import com.molyavin.expensetracker.presentation.bottom_sheet_dialog.transaction.widget.CategoryBlock

private val viewModel: EditTransactionBottomSheetViewModel =
    Injector.INSTANCE.provideEditTransactionViewModel()

@Composable
fun EditTransactionBottomSheetDialog(id: String, onClick: () -> Unit, isShowTransaction: Boolean) {
    viewModel.ObserveLifecycleEvents(LocalLifecycleOwner.current.lifecycle)
    viewModel.setId(id)
    val label by viewModel.label.collectAsState()
    val amount by viewModel.amount.collectAsState()
    val currency by viewModel.currency.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val dataTime by viewModel.dataTime.collectAsState()

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val scaffoldHeight = (screenHeight * 0.8f)

    Scaffold(
        modifier = Modifier
            .height(scaffoldHeight)
            .navigationBarsPadding()
            .statusBarsPadding()
            .imePadding(),
        containerColor = AppTheme.colors.onBackground.modal,
        bottomBar = {
            ButtonTransparent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = Spacing.S, top = Spacing.M, end = Spacing.S),
                onClick = {
                    if (label.isNotNull() && amount.isNotNull()) {
                        viewModel.editTransaction()
                        onClick()
                    }
                },
                text = if (isShowTransaction) "Close" else "Save"
            )
        }) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
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
                    value = amount,
                    onValueChange = viewModel::setAmount,
                    placeholder = stringResource(id = R.string.text_text_field_amount),
                    enabled = !isShowTransaction
                )
            }

            Text(
                modifier = Modifier.padding(horizontal = Spacing.M, vertical = Spacing.S),
                text = dataTime,
                color = Color.White,
                style = AppTheme.typography.s3
            )

            CategoryBlock(
                selectedCategory = selectedCategory,
                onCategorySelected = viewModel::setCategory,
                isActive = !isShowTransaction
            )

            Row(
                modifier = Modifier.padding(start = Spacing.M),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .clip(AppTheme.shapes.large)
                        .size(IconSize.L)
                        .background(colorBackgroundCategoryImage())
                        .padding(Spacing.S),
                    painter = painterResource(id = R.drawable.ic_notes_new),
                    contentDescription = null
                )

                CustomTextField(
                    value = label,
                    onValueChange = viewModel::setLabel,
                    placeholder = stringResource(id = R.string.text_placeholder_notes),
                    textStyle = AppTheme.typography.h3,
                    placeHolderTextStyle = AppTheme.typography.h3,
                    enabled = !isShowTransaction
                )
            }
        }
    }
}