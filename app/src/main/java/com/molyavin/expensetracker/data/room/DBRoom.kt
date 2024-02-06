package com.molyavin.expensetracker.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TransactionEntity::class], version = 2)
abstract class DBRoom : RoomDatabase() {

    abstract val dao: Dao

}