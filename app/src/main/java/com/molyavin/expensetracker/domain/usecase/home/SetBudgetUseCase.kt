package com.molyavin.expensetracker.domain.usecase.home

import com.molyavin.expensetracker.data.repository.BudgetRepository
import com.molyavin.expensetracker.data.repository.TransactionRepository
import com.molyavin.expensetracker.domain.model.Budget
import com.molyavin.expensetracker.domain.model.asDomain
import com.molyavin.expensetracker.domain.usecase.IAsyncUseCase
import javax.inject.Inject

class SetBudgetUseCase @Inject constructor(
    private val budgetRepository: BudgetRepository
) : IAsyncUseCase<Budget, Unit> {

    override suspend fun execute(income: Budget) {
        budgetRepository.setBudget(income.asDomain())
    }
}