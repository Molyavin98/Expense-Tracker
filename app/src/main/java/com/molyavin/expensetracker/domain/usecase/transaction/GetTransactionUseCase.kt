package com.molyavin.expensetracker.domain.usecase.transaction

import com.molyavin.expensetracker.data.room.DBRoom
import com.molyavin.expensetracker.data.local.model.TransactionDTO
import com.molyavin.expensetracker.domain.usecase.IAsyncUseCase
import com.molyavin.expensetracker.utils.AppDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class
fgiGetTransactionUseCase @Inject constructor(
    private val database: DBRoom,
    private val appDispatchers: AppDispatchers
) : IAsyncUseCase<Unit, Flow<List<TransactionDTO>>> {

    override suspend fun execute(income: Unit): Flow<List<TransactionDTO>> {
        return withContext(appDispatchers.io) {
            database.dao.getAllTransaction()
        }
    }
}