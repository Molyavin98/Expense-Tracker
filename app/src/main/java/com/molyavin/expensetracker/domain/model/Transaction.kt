package com.molyavin.expensetracker.domain.model

import com.molyavin.expensetracker.data.network.dto.TransactionNewDTO

data class Transaction(
    val id: String,
    val title: String,
    val amount: Double,
    val date: String,
    val categoryId: Int
)

fun Transaction.asDomain() = TransactionNewDTO(
    id = id,
    title = title,
    amount = amount,
    date = date,
    categoryId = categoryId
)