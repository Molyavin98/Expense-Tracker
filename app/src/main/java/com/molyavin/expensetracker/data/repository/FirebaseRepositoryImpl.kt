package com.molyavin.expensetracker.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.molyavin.expensetracker.data.network.dto.TransactionNewDTO
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val database: DatabaseReference,
    private val auth: FirebaseAuth
) : FirebaseRepository {

    private fun getUserId(): String? {
        return auth.currentUser?.uid
    }

    override suspend fun setTransactionItem(entity: TransactionNewDTO) {
        val userId = getUserId() ?: return
        val transactionId = database.child(USERS).child(userId).child(TRANSACTIONS).push().key
        if (transactionId != null) {
            database
                .child(USERS)
                .child(userId)
                .child(TRANSACTIONS)
                .child(transactionId)
                .setValue(entity.copy(id = transactionId))
        }
    }

    override suspend fun deleteTransactionItem(entity: TransactionNewDTO) {
        val userId = getUserId() ?: return
        database
            .child(USERS)
            .child(userId)
            .child(TRANSACTIONS)
            .child(entity.id ?: "")
            .removeValue()
    }

    override suspend fun updateITransactionItem(entity: TransactionNewDTO) {
        val userId = getUserId() ?: return
        database
            .child(USERS)
            .child(userId)
            .child(TRANSACTIONS)
            .child(entity.id?: "")
            .setValue(entity)
    }

    override suspend fun getTransactionItem(): List<TransactionNewDTO> {
        val userId = getUserId() ?: return emptyList()
        val transactions = mutableListOf<TransactionNewDTO>()
        val snapshot = database.child(USERS).child(userId).child(TRANSACTIONS).get().await()
        for (child in snapshot.children) {
            val transaction = child.getValue(TransactionNewDTO::class.java)
            if (transaction != null) {
                transactions.add(transaction)
            }
        }
        return transactions
    }

    override suspend fun getTransactionItemById(id: String): TransactionNewDTO? {
        val userId = getUserId() ?: return null
        val snapshot = database.child(USERS).child(userId).child(TRANSACTIONS).child(id).get().await()
        return snapshot.getValue(TransactionNewDTO::class.java)
    }



    private companion object {
        const val USERS = "users"
        const val TRANSACTIONS = "transactions"
    }

}