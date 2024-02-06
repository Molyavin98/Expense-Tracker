package com.molyavin.expensetracker.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Insert
    suspend fun insertTransactionItem(entity: TransactionEntity)

    @Delete
    suspend fun deleteTransactionItem(entity: TransactionEntity)

    @Update
    suspend fun updateITransactionItem(entity: TransactionEntity)

    @Query("SELECT * FROM TransactionEntity")
    fun getAllTransaction(): Flow<List<TransactionEntity>>
}