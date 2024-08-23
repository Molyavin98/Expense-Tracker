package com.molyavin.expensetracker.domain.usecase.home

import com.molyavin.expensetracker.data.network.dto.asPresentation
import com.molyavin.expensetracker.data.repository.BudgetRepository
import com.molyavin.expensetracker.data.repository.TransactionRepository
import com.molyavin.expensetracker.domain.model.Budget
import com.molyavin.expensetracker.domain.usecase.IAsyncUseCase
import javax.inject.Inject

class GetBudgetUseCase @Inject constructor(
    private val budgetRepository: BudgetRepository
) : IAsyncUseCase<Unit?, Budget> {

    override suspend fun execute(income: Unit?): Budget {
        return budgetRepository.getBudget().asPresentation()
    }
}