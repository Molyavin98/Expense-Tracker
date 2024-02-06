package com.molyavin.expensetracker.presentation.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.molyavin.expensetracker.R
import com.molyavin.expensetracker.design_system.AppTheme
import com.molyavin.expensetracker.design_system.ButtonAdd
import com.molyavin.expensetracker.design_system.DefaultText
import com.molyavin.expensetracker.design_system.DefaultTwoTextBox
import com.molyavin.expensetracker.design_system.Spacing
import com.molyavin.expensetracker.design_system.TopAppName
import com.molyavin.expensetracker.design_system.TransactionsItem
import com.molyavin.expensetracker.di.scope.Injector
import com.molyavin.expensetracker.domain.model.TransactionVM
import com.molyavin.expensetracker.presentation.screen.BaseScreen
import com.molyavin.expensetracker.presentation.viewmodel.home.HomeViewModel

class HomeScreen : BaseScreen() {

    override val viewModel: HomeViewModel = Injector.INSTANCE.provideHomeViewModel()

    @Composable
    override fun Content() {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.padding(
                    start = Spacing.M,
                    top = Spacing.S,
                    end = Spacing.M,
                    bottom = Spacing.M
                )
            ) {
                TopAppName(onClick = viewModel::nextScreenSetting)

                DefaultTwoTextBox(
                    textTitle = stringResource(id = R.string.total_balance),
                    textNumber = 218500.0
                )
                Spacer(modifier = Modifier.size(Spacing.S))

                Row(modifier = Modifier.fillMaxWidth()) {
                    DefaultTwoTextBox(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = Spacing.XS),
                        textTitle = stringResource(id = R.string.total_income),
                        colorSum = AppTheme.colors.primaryHover,
                        textNumber = 22000.0
                    )

                    DefaultTwoTextBox(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = Spacing.XS),
                        textTitle = stringResource(id = R.string.total_expense),
                        colorSum = AppTheme.colors.error,
                        textNumber = 15000.0
                    )
                }

                val transactions by viewModel.itemsList.collectAsState()
                val sortedTransactions = transactions.sortedByDescending { it.date }

                LazyColumn(
                    modifier = Modifier.wrapContentSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {

                    item {
                        Spacer(modifier = Modifier.size(Spacing.M))

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Image(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable { },
                                painter = painterResource(id = R.drawable.ic_filter),
                                contentDescription = null
                            )

                            Image(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable { viewModel.nextScreenStatistics() },
                                painter = painterResource(id = R.drawable.ic_statistics),
                                contentDescription = null
                            )
                        }

                        DefaultText(text = stringResource(id = R.string.recent_transactions))
                    }

                    items(sortedTransactions) {
                        TransactionsItem(
                            text = it.title,
                            sum = it.amount,
                            profit = it.isIncome,
                            date = it.date ?: "",
                            onDeleteClick = { viewModel.deleteItem(it) },
                            onEditClick = {
                                viewModel.nextScreenEdit(
                                    TransactionVM(
                                        id = it.id,
                                        label = it.title,
                                        amount = it.amount,
                                        isIncome = it.isIncome,
                                    )
                                )
                            }
                        )
                    }
                }
            }

            ButtonAdd(
                onClick = viewModel::nextScreenAddTransaction,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }

}
