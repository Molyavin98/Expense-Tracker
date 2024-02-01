package com.molyavin.expensetracker.presentation.screen.home

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.molyavin.expensetracker.design_system.AppTheme
import com.molyavin.expensetracker.design_system.ButtonAdd
import com.molyavin.expensetracker.design_system.DefaultButton
import com.molyavin.expensetracker.design_system.DefaultText
import com.molyavin.expensetracker.design_system.DefaultTwoTextBox
import com.molyavin.expensetracker.design_system.Spacing
import com.molyavin.expensetracker.design_system.TopAppName
import com.molyavin.expensetracker.design_system.TransactionsItem
import com.molyavin.expensetracker.di.scope.Injector
import com.molyavin.expensetracker.presentation.screen.BaseActivity
import com.molyavin.expensetracker.presentation.viewmodel.home.HomeViewModel

class HomeScreen : BaseActivity() {

    override val viewModel: HomeViewModel by lazy {
        Injector.INSTANCE.provideHomeViewModel()
    }

    @Composable
    override fun Content() {
        Box(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
            ) {
                TopAppName(onClick = { viewModel.startSetting() })

                DefaultTwoTextBox(textTitle = "Total Balance", textNumber = 218500.0)
                Spacer(modifier = Modifier.size(Spacing.S))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    DefaultTwoTextBox(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 4.dp),
                        textTitle = "Total Income",
                        colorSum = AppTheme.colors.primaryHover,
                        textNumber = 22000.0
                    )

                    DefaultTwoTextBox(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 4.dp),
                        textTitle = "Total Expense",
                        colorSum = AppTheme.colors.error,
                        textNumber = 15000.0
                    )
                }

                Spacer(modifier = Modifier.size(Spacing.M))
                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(),
                    shape = AppTheme.shapes.small,
                    text = "View statistics",
                    onClick = { })

                DefaultText(text = "Recent transactions:")

                LazyColumn(
                    modifier = Modifier.wrapContentSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {

                    items(10) {
                        TransactionsItem(
                            text = "Mac",
                            sum = 250.0,
                            profit = if (it % 2 == 0) true else false
                        )
                    }
                }
            }

            ButtonAdd(
                onClick = { viewModel.addTransaction() },
                modifier = Modifier.align(Alignment.BottomEnd)
            )

        }

    }
}
