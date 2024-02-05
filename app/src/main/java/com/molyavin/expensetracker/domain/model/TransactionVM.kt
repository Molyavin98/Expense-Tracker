package com.molyavin.expensetracker.domain.model

data class TransactionVM(val label: String, val amount: String, val isIncome: Boolean)