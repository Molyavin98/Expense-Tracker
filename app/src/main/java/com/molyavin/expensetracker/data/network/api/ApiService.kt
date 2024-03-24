package com.molyavin.expensetracker.data.network.api

import com.molyavin.expensetracker.data.network.dto.CurrencyDTO
import retrofit2.http.GET

interface ApiService {

    @GET("currency")
    suspend fun getCurrency(): List<CurrencyDTO>
}