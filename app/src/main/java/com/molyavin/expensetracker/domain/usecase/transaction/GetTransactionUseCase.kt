package com.molyavin.expensetracker.domain.usecase.transaction

import com.molyavin.expensetracker.data.repository.FirebaseRepository
import com.molyavin.expensetracker.data.network.dto.asPresentation
import com.molyavin.expensetracker.domain.model.Transaction
import com.molyavin.expensetracker.domain.usecase.IAsyncUseCase
import com.molyavin.expensetracker.utils.AppDispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetTransactionUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository,
    private val appDispatchers: AppDispatchers
) : IAsyncUseCase<Unit, List<Transaction>> {

    override suspend fun execute(income: Unit): List<Transaction> {
        return withContext(appDispatchers.io) {
            firebaseRepository.getTransactionItem().map { it.asPresentation() }
        }
    }
}