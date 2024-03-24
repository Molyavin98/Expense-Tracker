package com.molyavin.expensetracker.data.network.api

import com.molyavin.expensetracker.data.network.dto.CurrencyDTO

interface ApiManagerType {

    suspend fun getCurrency(): List<CurrencyDTO>
}