package com.molyavin.expensetracker.data.network.dto

import com.molyavin.expensetracker.domain.model.Budget

data class BudgetDTO(
    val budget: Double? = null,
)

fun BudgetDTO.asPresentation() = Budget(
    budget = budget ?: 0.0
)