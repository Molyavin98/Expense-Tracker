package com.molyavin.expensetracker.domain.usecase.transaction

import com.molyavin.expensetracker.data.repository.TransactionRepository
import com.molyavin.expensetracker.data.network.dto.TransactionNewDTO
import com.molyavin.expensetracker.domain.usecase.IAsyncUseCase
import javax.inject.Inject

class AddTransactionUseCase @Inject constructor(
    private val transactionRepository : TransactionRepository,
) : IAsyncUseCase<TransactionNewDTO, Unit> {

    override suspend fun execute(income: TransactionNewDTO) {
        transactionRepository.setTransactionItem(income)
    }
}