package com.molyavin.expensetracker.data.repository

interface CurrencyRepository {

    suspend fun setCurrency(currency: String)

    suspend fun getCurrency(): String
}