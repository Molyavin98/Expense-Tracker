package com.molyavin.expensetracker.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val amount: Float,
    val isIncome: Boolean,
    val date:String? = null
)
