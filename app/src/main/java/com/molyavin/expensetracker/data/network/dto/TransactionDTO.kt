package com.molyavin.expensetracker.data.network.dto

import com.molyavin.expensetracker.domain.model.Transaction

data class TransactionNewDTO(
    val id: String? = null,
    val amount: Double? = null,
    val title: String? = null,
    val income: Boolean? = null,
    val date: String? = null
)

fun TransactionNewDTO.asPresentation() = Transaction(
    id = id.orEmpty(),
    amount = amount ?: 0.0,
    title = title.orEmpty(),
    income = income ?: false,
    date = date.orEmpty()
)
