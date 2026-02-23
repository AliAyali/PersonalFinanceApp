package com.aliayali.personalfinanceapp.data.repository

import com.aliayali.personalfinanceapp.data.local.database.dao.AccountDao
import com.aliayali.personalfinanceapp.data.local.database.entity.AccountEntity
import com.aliayali.personalfinanceapp.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val dao: AccountDao,
) : AccountRepository {
    override suspend fun insert(account: AccountEntity) {
        return dao.insert(account)
    }

    override suspend fun update(account: AccountEntity) {
        dao.update(account)
    }

    override suspend fun delete(account: AccountEntity) {
        dao.delete(account)
    }

    override suspend fun getById(id: Long): AccountEntity? =
        dao.getById(id)


    override suspend fun updateBalance(accountId: Long, amount: Long) {
        dao.updateBalance(accountId, amount)
    }

    override fun getTotalBalance(): Flow<Long?> =
        dao.getTotalBalance()


    override fun getAllAccounts(): Flow<List<AccountEntity>> =
        dao.getAllAccounts()

    override suspend fun isCardNumberExists(cardNumber: String): Boolean =
        dao.isCardNumberExists(cardNumber)
}