package com.aliayali.personalfinanceapp.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aliayali.personalfinanceapp.data.local.database.MyDataBase

@Entity(tableName = MyDataBase.NOTE_TABLE)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0L,
    @ColumnInfo(name = "detail") val detail: String,
    @ColumnInfo(name = "date") val date: String,
)