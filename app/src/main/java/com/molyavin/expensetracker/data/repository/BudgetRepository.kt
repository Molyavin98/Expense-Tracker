package com.molyavin.expensetracker.data.repository

import com.molyavin.expensetracker.data.network.dto.BudgetDTO

interface BudgetRepository {

    suspend fun setBudget(entity: BudgetDTO)

    suspend fun getBudget(): BudgetDTO
}