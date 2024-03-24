package com.molyavin.expensetracker.domain.usecase.transaction

import com.molyavin.expensetracker.data.room.DBRoom
import com.molyavin.expensetracker.data.local.model.TransactionDTO
import com.molyavin.expensetracker.domain.usecase.IAsyncUseCase
import javax.inject.Inject

class DeleteTransactionUseCase @Inject constructor(
    private val dataBase: DBRoom
) : IAsyncUseCase<TransactionDTO, Unit> {
    override suspend fun execute(income: TransactionDTO) {
        dataBase.dao.deleteTransactionItem(income)
    }
}