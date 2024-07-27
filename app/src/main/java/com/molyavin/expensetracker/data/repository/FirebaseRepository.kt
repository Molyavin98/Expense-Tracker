package com.molyavin.expensetracker.data.repository

import com.molyavin.expensetracker.data.network.dto.TransactionNewDTO

interface FirebaseRepository {

    suspend fun setTransactionItem(entity: TransactionNewDTO)

    suspend fun deleteTransactionItem(entity: TransactionNewDTO)

    suspend fun updateITransactionItem(entity: TransactionNewDTO)

    suspend fun getTransactionItem(): List<TransactionNewDTO>

    suspend fun getTransactionItemById(id: String): TransactionNewDTO?
}