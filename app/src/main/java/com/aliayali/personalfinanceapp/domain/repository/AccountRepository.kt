package com.aliayali.personalfinanceapp.domain.repository

import com.aliayali.personalfinanceapp.data.local.database.entity.AccountEntity
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun insert(account: AccountEntity)
    suspend fun update(account: AccountEntity)
    suspend fun delete(account: AccountEntity)
    suspend fun getById(id: Long): AccountEntity?
    suspend fun updateBalance(accountId: Long, amount: Long)
    fun getTotalBalance(): Flow<Long?>
    fun getAllAccounts(): Flow<List<AccountEntity>>
}