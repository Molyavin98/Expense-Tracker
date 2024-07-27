package com.molyavin.expensetracker.domain.usecase.transaction

import com.molyavin.expensetracker.data.repository.FirebaseRepository
import com.molyavin.expensetracker.domain.model.Transaction
import com.molyavin.expensetracker.domain.model.asDomain
import com.molyavin.expensetracker.domain.usecase.IAsyncUseCase
import javax.inject.Inject

class DeleteTransactionUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) : IAsyncUseCase<Transaction, Unit> {
    override suspend fun execute(income: Transaction) {
        firebaseRepository.deleteTransactionItem(income.asDomain())
    }
}