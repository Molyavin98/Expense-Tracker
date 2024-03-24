package com.molyavin.expensetracker.data.network.api

import com.molyavin.expensetracker.data.network.dto.CurrencyDTO
import javax.inject.Inject

class ApiManager @Inject constructor(
    private val apiService: ApiService
) : ApiManagerType {


    override suspend fun getCurrency(): List<CurrencyDTO> {
        return apiService.getCurrency()
    }
}