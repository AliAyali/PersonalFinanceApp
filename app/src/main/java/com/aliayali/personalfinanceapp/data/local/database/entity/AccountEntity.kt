package com.aliayali.personalfinanceapp.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aliayali.personalfinanceapp.data.local.database.MyDataBase
import com.aliayali.personalfinanceapp.data.local.database.model.AccountType

@Entity(tableName = MyDataBase.ACCOUNT_TABLE)
data class AccountEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0L,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "type") val type: AccountType,
    @ColumnInfo(name = "cardNumber") val cardNumber: String? = null,
    @ColumnInfo(name = "iconName") val iconName: String,
    @ColumnInfo(name = "initialBalance") val initialBalance: Long = 0L,
    @ColumnInfo(name = "currentBalance") val currentBalance: Long = 0L,
    @ColumnInfo(name = "createdAt") val createdAt: Long = System.currentTimeMillis(),
)