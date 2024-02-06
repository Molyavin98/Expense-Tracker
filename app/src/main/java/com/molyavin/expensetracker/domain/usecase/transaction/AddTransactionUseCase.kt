package com.molyavin.expensetracker.domain.usecase.transaction

import com.molyavin.expensetracker.data.room.DBRoom
import com.molyavin.expensetracker.data.room.TransactionEntity
import com.molyavin.expensetracker.domain.usecase.base.IAsyncUseCase
import javax.inject.Inject

class AddTransactionUseCase @Inject constructor(private val database: DBRoom) :
    IAsyncUseCase<TransactionEntity, Unit> {
    override suspend fun execute(income: TransactionEntity) {
        database.dao.insertTransactionItem(income)
        database.dao.getAllTransaction()
    }
}