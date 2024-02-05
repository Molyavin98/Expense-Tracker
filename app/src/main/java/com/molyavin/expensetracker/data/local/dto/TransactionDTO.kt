package com.molyavin.expensetracker.data.local.dto

import com.molyavin.expensetracker.domain.model.TransactionVM

data class TransactionDTO(val label: String, val amount: String, val isIncome: Boolean)

fun TransactionVM.toDTO(): TransactionDTO {
    return TransactionDTO(label = label, amount = amount, isIncome = isIncome)
}