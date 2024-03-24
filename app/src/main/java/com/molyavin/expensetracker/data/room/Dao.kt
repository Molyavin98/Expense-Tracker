package com.molyavin.expensetracker.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.molyavin.expensetracker.data.local.model.TransactionDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Insert
    suspend fun insertTransactionItem(entity: TransactionDTO)

    @Delete
    suspend fun deleteTransactionItem(entity: TransactionDTO)

    @Update
    suspend fun updateITransactionItem(entity: TransactionDTO)

    @Query("SELECT * FROM TransactionDTO")
    fun getAllTransaction(): Flow<List<TransactionDTO>>
}