package com.molyavin.expensetracker.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.molyavin.expensetracker.data.network.dto.BudgetDTO
import kotlinx.coroutines.tasks.await

class BudgetRepositoryImpl(
    private val database: DatabaseReference,
    private val auth: FirebaseAuth
) : BudgetRepository {

    private fun getUserId(): String? {
        return auth.currentUser?.uid
    }

    override suspend fun setBudget(entity: BudgetDTO) {
        val userId = getUserId() ?: return
        database
            .child(USERS)
            .child(userId)
            .child(BUDGET)
            .setValue(entity)
    }

    override suspend fun getBudget(): BudgetDTO {
        val userId = getUserId() ?: return BudgetDTO()
        val snapshot = database.child(USERS).child(userId).child(BUDGET).get().await()
        return snapshot.getValue(BudgetDTO::class.java) ?: BudgetDTO()
    }

    private companion object {
        const val USERS = "users"
        const val BUDGET = "budget"
    }
}