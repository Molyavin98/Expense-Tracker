package com.molyavin.expensetracker.domain.usecase.setting

import com.molyavin.expensetracker.data.repository.CurrencyRepository
import com.molyavin.expensetracker.data.repository.TransactionRepository
import com.molyavin.expensetracker.domain.usecase.IAsyncUseCase
import javax.inject.Inject

class GetCurrencyUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository
) : IAsyncUseCase<Unit?, String> {

    override suspend fun execute(income: Unit?): String {
        return currencyRepository.getCurrency()
    }
}