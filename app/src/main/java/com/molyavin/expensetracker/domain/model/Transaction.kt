package com.molyavin.expensetracker.domain.model

import com.molyavin.expensetracker.data.network.dto.TransactionNewDTO

data class Transaction(
    val id: String,
    val title: String,
    val amount: Double,
    val income: Boolean,
    val date: String
)

fun Transaction.asDomain() = TransactionNewDTO(
    id = id,
    title = title,
    amount = amount,
    income = income,
    date = date
)