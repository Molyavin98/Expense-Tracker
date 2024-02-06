package com.molyavin.expensetracker.data.local.dto

import com.molyavin.expensetracker.domain.model.TransactionVM

data class TransactionDTO(val id: Int?, val label: String, val amount: Float, val isIncome: Boolean)

fun TransactionVM.toDTO(): TransactionDTO {
    return TransactionDTO(id = id, label = label, amount = amount, isIncome = isIncome)
}