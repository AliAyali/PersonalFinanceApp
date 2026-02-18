package com.aliayali.personalfinanceapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aliayali.personalfinanceapp.data.local.database.MyDataBase.Companion.DATABASE_VERSION
import com.aliayali.personalfinanceapp.data.local.database.dao.AccountDao
import com.aliayali.personalfinanceapp.data.local.database.entity.AccountEntity

@Database(
    entities = [
        AccountEntity::class,
    ],
    version = DATABASE_VERSION
)
abstract class MyDataBase : RoomDatabase() {
    abstract fun accountDao(): AccountDao

    companion object {
        const val DATABASE_NAME = "market_db"
        const val ACCOUNT_TABLE = "account_table"
        const val DATABASE_VERSION = 1
    }
}