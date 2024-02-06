package com.molyavin.expensetracker.domain.usecase.transaction

import com.molyavin.expensetracker.data.room.DBRoom
import com.molyavin.expensetracker.data.room.TransactionEntity
import com.molyavin.expensetracker.domain.usecase.base.IAsyncUseCase
import com.molyavin.expensetracker.utils.AppDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetTransactionUseCase @Inject constructor(
    private val database: DBRoom,
    private val appDispatchers: AppDispatchers
) : IAsyncUseCase<Unit, Flow<List<TransactionEntity>>> {
    override suspend fun execute(income: Unit): Flow<List<TransactionEntity>> {
        return withContext(appDispatchers.io) {
            database.dao.getAllTransaction()
        }
    }
}