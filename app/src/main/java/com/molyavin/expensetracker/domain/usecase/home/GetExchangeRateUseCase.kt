package com.molyavin.expensetracker.domain.usecase.home

import com.molyavin.expensetracker.data.network.api.ApiManagerType
import com.molyavin.expensetracker.data.network.dto.asPresentation
import com.molyavin.expensetracker.domain.model.Currency
import com.molyavin.expensetracker.domain.usecase.IAsyncUseCase
import javax.inject.Inject

class GetExchangeRateUseCase @Inject constructor(
    private val apiManagerType: ApiManagerType
) : IAsyncUseCase<Unit?, List<Currency>> {

    override suspend fun execute(income: Unit?): List<Currency> {
        return apiManagerType.getCurrency().asPresentation()
    }
}