package com.molyavin.expensetracker.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await

class CurrencyRepositoryImpl(
    private val database: DatabaseReference,
    private val auth: FirebaseAuth
) : CurrencyRepository {

    private fun getUserId(): String? {
        return auth.currentUser?.uid
    }

    override suspend fun setCurrency(currency: String) {
        val userId = getUserId() ?: return
        database
            .child(USERS)
            .child(userId)
            .child(CURRENCY)
            .setValue(currency)
    }

    override suspend fun getCurrency(): String {
        val userId = getUserId() ?: return ""
        val snapshot = database.child(USERS).child(userId).child(CURRENCY).get().await()
        return snapshot.getValue(String::class.java) ?: ""
    }

    private companion object {
        const val USERS = "users"
        const val CURRENCY = "currency"
    }
}