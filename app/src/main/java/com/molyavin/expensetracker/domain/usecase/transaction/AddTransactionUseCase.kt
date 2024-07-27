package com.molyavin.expensetracker.domain.usecase.transaction

import com.molyavin.expensetracker.data.repository.FirebaseRepository
import com.molyavin.expensetracker.data.network.dto.TransactionNewDTO
import com.molyavin.expensetracker.domain.usecase.IAsyncUseCase
import javax.inject.Inject

class AddTransactionUseCase @Inject constructor(
    private val firebaseRepository : FirebaseRepository,
) : IAsyncUseCase<TransactionNewDTO, Unit> {

    override suspend fun execute(income: TransactionNewDTO) {
        firebaseRepository.setTransactionItem(income)
    }
}