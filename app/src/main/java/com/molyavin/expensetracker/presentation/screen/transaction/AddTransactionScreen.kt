package com.molyavin.expensetracker.presentation.screen.transaction

import androidx.compose.runtime.Composable
import com.molyavin.expensetracker.di.scope.Injector
import com.molyavin.expensetracker.presentation.screen.BaseActivity
import com.molyavin.expensetracker.presentation.viewmodel.add_transaction.AddTransactionViewModel

class AddTransactionScreen : BaseActivity() {

    override val viewModel: AddTransactionViewModel =
        Injector.INSTANCE.provideAddTransactionViewModel()

    @Composable
    override fun Content() {

    }
}