package com.molyavin.expensetracker.domain.model

data class Currency(
    val currencyCodeA: Int,
    val currencyCodeB: Int,
    val date: Long,
    val rateSell: Float,
    val rateBuy: Float,
    val reteCross: Float,
)



