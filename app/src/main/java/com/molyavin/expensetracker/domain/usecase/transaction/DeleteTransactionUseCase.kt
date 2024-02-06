package com.molyavin.expensetracker.domain.usecase.transaction

import com.molyavin.expensetracker.data.room.DBRoom
import com.molyavin.expensetracker.data.room.TransactionEntity
import com.molyavin.expensetracker.domain.usecase.base.IAsyncUseCase
import javax.inject.Inject

class DeleteTransactionUseCase @Inject constructor(
    private val dataBase: DBRoom
) : IAsyncUseCase<TransactionEntity, Unit> {
    override suspend fun execute(income: TransactionEntity) {
        dataBase.dao.deleteTransactionItem(income)
    }
}