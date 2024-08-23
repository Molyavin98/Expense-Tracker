package com.molyavin.expensetracker.presentation.bottom_sheet_dialog.transaction

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import co.yml.charts.common.extensions.isNotNull
import com.molyavin.expensetracker.R
import com.molyavin.expensetracker.design_system.AppTheme
import com.molyavin.expensetracker.design_system.ButtonTransparent
import com.molyavin.expensetracker.design_system.IconSize
import com.molyavin.expensetracker.design_system.Spacing
import com.molyavin.expensetracker.design_system.colorBackgroundCategoryImage
import com.molyavin.expensetracker.di.scope.Injector
import com.molyavin.expensetracker.domain.model.mapCategories
import com.molyavin.expensetracker.presentation.ObserveLifecycleEvents


private val viewModel: AddTransactionBottomSheetViewModel =
    Injector.INSTANCE.provideAddTransactionViewModel()

@Composable
fun AddTransactionBottomSheetDialog(onClick: () -> Unit) {
    viewModel.ObserveLifecycleEvents(LocalLifecycleOwner.current.lifecycle)
    val label by viewModel.label.collectAsState()
    val amount by viewModel.amount.collectAsState()
    val currency by viewModel.currency.collectAsState()

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
                    .padding(horizontal = Spacing.S)
                    .imePadding(),
                onClick = {
                    if (label.isNotNull() && amount.isNotNull()) {
                        viewModel.addTransactionItem()
                        onClick()
                    }
                },
                text = stringResource(id = R.string.text_add_transaction)
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
                    placeholder = stringResource(id = R.string.text_text_field_amount)
                )
            }

            CategoryBlock()

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
                    painter = painterResource(id =R.drawable.ic_notes_new),
                    contentDescription = null
                )

                CustomTextField(
                    value = label,
                    onValueChange = viewModel::setLabel,
                    placeholder = stringResource(id = R.string.text_placeholder_notes),
                    textStyle = AppTheme.typography.h3,
                    placeHolderTextStyle = AppTheme.typography.h3
                )
            }
        }
    }
}

@Composable
fun CustomTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    placeholder: String? = null,
    textStyle: TextStyle = AppTheme.typography.h1,
    placeHolderTextStyle: TextStyle = AppTheme.typography.h1
) {
    TextField(
        modifier = Modifier.wrapContentHeight(),
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder ?: "",
                color = AppTheme.colors.onSurface.mediumGrey,
                style = placeHolderTextStyle
            )
        },
        textStyle = textStyle,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = AppTheme.colors.onSurface.primary,
            textColor = AppTheme.colors.onSurface.primary,
        ),
    )
}


@Composable
fun CategoryBlock() {
    var expanded by remember { mutableStateOf(false) }
    val categoryList = mapCategories()
    val selectedCategory by viewModel.selectedCategory.collectAsState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Spacing.M)
            .clickable {
                expanded = true
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .clip(AppTheme.shapes.large)
                .size(IconSize.L)
                .background(colorBackgroundCategoryImage())
                .padding(Spacing.S),
            painter = painterResource(id = categoryList[selectedCategory].imageResId),
            contentDescription = null
        )

        Spacer(modifier = Modifier.size(Spacing.M))
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(id = categoryList[selectedCategory].name),
            style = AppTheme.typography.h5,
            color = AppTheme.colors.onSurface.primary
        )
        Icon(
            imageVector = if (expanded) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowRight,
            contentDescription = null
        )
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
    ) {
        categoryList.forEachIndexed { index, category ->
            DropdownMenuItem(
                onClick = {
                    viewModel.setCategory(index)
                    expanded = false
                }) {
                Image(
                    modifier = Modifier
                        .size(IconSize.M)
                        .clip(AppTheme.shapes.large)
                        .background(colorBackgroundCategoryImage())
                        .padding(Spacing.S),
                    painter = painterResource(id = category.imageResId),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(Spacing.L))
                Text(
                    text = stringResource(id = category.name),
                    style = AppTheme.typography.s2
                )
            }
        }
    }
}
