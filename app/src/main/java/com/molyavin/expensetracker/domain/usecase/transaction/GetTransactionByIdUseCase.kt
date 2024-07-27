package com.molyavin.expensetracker.domain.usecase.transaction

import com.molyavin.expensetracker.data.repository.FirebaseRepository
import com.molyavin.expensetracker.data.network.dto.asPresentation
import com.molyavin.expensetracker.domain.model.Transaction
import com.molyavin.expensetracker.domain.usecase.IAsyncUseCase
import javax.inject.Inject

class GetTransactionByIdUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) : IAsyncUseCase<String, Transaction?> {

    override suspend fun execute(income: String): Transaction? {
        return firebaseRepository.getTransactionItemById(income)?.asPresentation()
    }
}