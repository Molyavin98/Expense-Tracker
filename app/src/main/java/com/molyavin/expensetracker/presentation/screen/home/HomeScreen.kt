package com.molyavin.expensetracker.presentation.screen.home

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.molyavin.expensetracker.R
import com.molyavin.expensetracker.design_system.AppTheme
import com.molyavin.expensetracker.design_system.BottomSheetDefault
import com.molyavin.expensetracker.design_system.DefaultTwoTextBox
import com.molyavin.expensetracker.design_system.Spacing
import com.molyavin.expensetracker.design_system.TabSwitcher
import com.molyavin.expensetracker.design_system.TransactionsItem
import com.molyavin.expensetracker.di.scope.Injector
import com.molyavin.expensetracker.presentation.BaseSettingsScreen
import com.molyavin.expensetracker.presentation.ObserveLifecycleEvents
import com.molyavin.expensetracker.presentation.bottom_sheet_dialog.transaction.AddBudgetBottomSheetDialog
import com.molyavin.expensetracker.presentation.bottom_sheet_dialog.transaction.AddTransactionBottomSheetDialog
import com.molyavin.expensetracker.presentation.bottom_sheet_dialog.transaction.EditTransactionBottomSheetDialog
import com.molyavin.expensetracker.utils.dateFormatter

private val viewModel: HomeViewModel = Injector.INSTANCE.provideHomeViewModel()

@Composable
fun HomeScreen(navController: NavController) {
    viewModel.ObserveLifecycleEvents(LocalLifecycleOwner.current.lifecycle)

    val isLoading by viewModel.isLoading.collectAsState()
    val budget by viewModel.budget.collectAsState()
    val transactions by viewModel.itemsTransactionList.collectAsState()
    val screenState by viewModel.screenState.collectAsState()
    val currency by viewModel.currency.collectAsState()
    val totalAmount by viewModel.totalAmount.collectAsState()

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    val listState = rememberLazyListState()
    val isExpanded = remember { derivedStateOf { listState.firstVisibleItemScrollOffset > 1 } }
    val animatedHeight by animateDpAsState(
        targetValue = if (isExpanded.value) screenHeight else screenHeight / 1.3f, label = ""
    )

    BaseSettingsScreen(
        isLoading = isLoading,
        navController = navController,
        onShowBottomSheet = { viewModel.setBottomSheetAddTransaction(true) }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.highlight.mediumSeaGreen)
        ) {
            Column(modifier = Modifier.height(screenHeight - animatedHeight)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(Spacing.L)
                            .clickable { viewModel.nextScreenSetting(navController) },
                        tint = AppTheme.colors.onBackground.primary,
                        imageVector = Icons.Default.Settings,
                        contentDescription = null
                    )
                }

                DefaultTwoTextBox(
                    textTitle = stringResource(id = R.string.my_budget),
                    textNumber = "$currency $budget",
                    onClick = { viewModel.setBottomSheetAddBudget(true) }
                )
                Spacer(modifier = Modifier.size(Spacing.S))
            }

            val cornerShape = if (isExpanded.value) RoundedCornerShape(0.dp)
            else RoundedCornerShape(topStart = Spacing.L, topEnd = Spacing.L)

            LazyColumn(
                state = listState,
                modifier = Modifier
                    .height(animatedHeight)
                    .fillMaxWidth()
                    .clip(cornerShape)
                    .background(AppTheme.colors.onBackground.modal)
                    .padding(horizontal = Spacing.M)
                    .padding(bottom = Spacing.M),
                verticalArrangement = Arrangement.spacedBy(Spacing.M),
            ) {
                item {
                    TabSwitcher(
                        firstTitle = "Today",
                        secondTitle = "All",
                        state = screenState != HistoryMode.TODAY,
                        onStateChanged = {
                            viewModel.onScreenStateChanged(it)
                            viewModel.fetchReload()
                        }
                    )
                }

                item {
                    if (screenState == HistoryMode.TODAY) {
                        Row(
                            modifier = Modifier.padding(bottom = Spacing.S),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier.weight(1f),
                                text = dateFormatter(),
                                style = AppTheme.typography.h3,
                            )

                            Text(text = "$currency $totalAmount", style = AppTheme.typography.h3)
                        }
                    }
                }

                items(transactions) { transaction ->
                    Crossfade(targetState = screenState, label = "") { targetState ->
                        when (targetState) {
                            HistoryMode.TODAY -> {
                                TransactionsItem(
                                    sum = "${transaction.amount} $currency",
                                    onDeleteClick = { viewModel.deleteItem(transaction) },
                                    categoryId = transaction.categoryId,
                                    onEditClick = {
                                        viewModel.setBottomSheetEditTransaction(true)
                                        viewModel.setId(transaction.id)
                                    }
                                )
                            }

                            HistoryMode.MONTH -> {
                                TransactionsItem(
                                    sum = "${transaction.amount} $currency",
                                    onDeleteClick = { viewModel.deleteItem(transaction) },
                                    categoryId = transaction.categoryId,
                                    isAllTab = true,
                                    onShowClick = {
                                        viewModel.setBottomSheetShowTransaction(true)
                                        viewModel.setId(transaction.id)
                                    }
                                )
                            }
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.size(Spacing.XXXXL))
                }
            }
        }

        BottomSheetDialog()
    }
}

@Composable
private fun BottomSheetDialog() {
    val bottomSheetAddTransaction by viewModel.showBottomSheetAddTransaction.collectAsState()
    val bottomSheetAddBudget by viewModel.showBottomSheetAddBudget.collectAsState()
    val bottomSheetEditTransaction by viewModel.showBottomSheetEditTransaction.collectAsState()
    val bottomSheetShowTransaction by viewModel.showBottomSheetShowTransaction.collectAsState()

    if (bottomSheetAddTransaction) {
        BottomSheetDefault(
            skipPartiallyExpanded = true,
            onDismiss = { viewModel.setBottomSheetAddTransaction(false) },
            content = {
                AddTransactionBottomSheetDialog(
                    onClick = {
                        viewModel.setBottomSheetAddTransaction(false)
                        viewModel.fetchReload()
                    },
                )
            }
        )
    }

    if (bottomSheetAddBudget) {
        BottomSheetDefault(
            onDismiss = { viewModel.setBottomSheetAddBudget(false) },
            content = {
                AddBudgetBottomSheetDialog(
                    onClick = {
                        viewModel.setBottomSheetAddBudget(false)
                        viewModel.fetchReload()
                    }
                )
            }
        )
    }

    if (bottomSheetEditTransaction || bottomSheetShowTransaction) {
        val id by viewModel.id.collectAsState()

        BottomSheetDefault(
            skipPartiallyExpanded = true,
            onDismiss = {
                viewModel.setBottomSheetEditTransaction(false)
                viewModel.setBottomSheetShowTransaction(false)
            },
            content = {
                EditTransactionBottomSheetDialog(
                    id = id,
                    isShowTransaction = bottomSheetShowTransaction,
                    onClick = {
                        viewModel.setBottomSheetEditTransaction(false)
                        viewModel.setBottomSheetShowTransaction(false)
                        viewModel.fetchReload()
                    })
            }
        )
    }

}





