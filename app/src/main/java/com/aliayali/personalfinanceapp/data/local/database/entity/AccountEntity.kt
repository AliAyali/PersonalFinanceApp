package com.aliayali.personalfinanceapp.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aliayali.personalfinanceapp.data.local.database.MyDataBase
import com.aliayali.personalfinanceapp.data.local.database.model.AccountType

@Entity(tableName = MyDataBase.ACCOUNT_TABLE)
data class AccountEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String,
    val type: AccountType,
    val cardNumber: String? = null,
    val iconName: String,
    val initialBalance: Long = 0L,
    val currentBalance: Long = 0L,
    val createdAt: Long = System.currentTimeMillis(),
)