package com.molyavin.expensetracker.domain.model

import com.molyavin.expensetracker.data.network.dto.BudgetDTO

data class Budget(
    val budget: Double
)

fun Budget.asDomain() = BudgetDTO(budget = budget)
