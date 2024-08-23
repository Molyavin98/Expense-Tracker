package com.molyavin.expensetracker.domain.usecase.transaction

import com.molyavin.expensetracker.data.repository.TransactionRepository
import com.molyavin.expensetracker.domain.model.Transaction
import com.molyavin.expensetracker.domain.model.asDomain
import com.molyavin.expensetracker.domain.usecase.IAsyncUseCase
import javax.inject.Inject

class EditTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) : IAsyncUseCase<Transaction, Unit> {

    override suspend fun execute(income: Transaction) {
        transactionRepository.updateITransactionItem(income.asDomain())
    }
}