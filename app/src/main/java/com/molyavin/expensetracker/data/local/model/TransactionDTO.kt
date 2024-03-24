package com.molyavin.expensetracker.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TransactionDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val amount: Float,
    val isIncome: Boolean,
    val date: String? = null,
)



