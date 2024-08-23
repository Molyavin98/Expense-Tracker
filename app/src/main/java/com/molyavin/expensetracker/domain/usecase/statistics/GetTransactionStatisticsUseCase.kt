package com.molyavin.expensetracker.domain.usecase.statistics

import com.molyavin.expensetracker.domain.model.Statistics
import com.molyavin.expensetracker.domain.model.Transaction
import com.molyavin.expensetracker.domain.usecase.IAsyncUseCase
import javax.inject.Inject

class GetTransactionStatisticsUseCase @Inject constructor() :
    IAsyncUseCase<Transaction, Statistics> {

    override suspend fun execute(income: Transaction): Statistics {
        return Statistics(
            totalIncome = income.amount,
            totalExpense = income.amount,
            totalBalance = income.amount
        )

    }
}