package com.molyavin.expensetracker.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.molyavin.expensetracker.data.local.model.TransactionDTO

@Database(entities = [TransactionDTO::class], version = 2)
abstract class DBRoom : RoomDatabase() {

    abstract val dao: Dao

}