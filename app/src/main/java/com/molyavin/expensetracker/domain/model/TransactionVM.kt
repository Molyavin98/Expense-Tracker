package com.molyavin.expensetracker.domain.model

data class TransactionVM(
    val id: Int?,
    val label: String,
    val amount: Float,
    val isIncome: Boolean,
    val date: String? = null
)