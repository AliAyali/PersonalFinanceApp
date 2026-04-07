package com.aliayali.personalfinanceapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aliayali.personalfinanceapp.data.local.database.MyDataBase.Companion.DATABASE_VERSION
import com.aliayali.personalfinanceapp.data.local.database.dao.AccountDao
import com.aliayali.personalfinanceapp.data.local.database.dao.NoteDao
import com.aliayali.personalfinanceapp.data.local.database.entity.AccountEntity
import com.aliayali.personalfinanceapp.data.local.database.entity.NoteEntity

@Database(
    entities = [
        AccountEntity::class,
        NoteEntity::class,
    ],
    version = DATABASE_VERSION
)
abstract class MyDataBase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun noteDao(): NoteDao

    companion object {
        const val DATABASE_NAME = "market_db"
        const val ACCOUNT_TABLE = "account_table"
        const val NOTE_TABLE = "note_table"
        const val DATABASE_VERSION = 3
    }
}