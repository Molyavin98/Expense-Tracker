package com.molyavin.expensetracker.domain.usecase.setting

import com.molyavin.expensetracker.data.repository.CurrencyRepository
import com.molyavin.expensetracker.data.repository.TransactionRepository
import com.molyavin.expensetracker.domain.usecase.IAsyncUseCase
import javax.inject.Inject

class SetCurrencyUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository
) : IAsyncUseCase<String, Unit> {

    override suspend fun execute(income: String) {
        currencyRepository.setCurrency(income)
    }

}