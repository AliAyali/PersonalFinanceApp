package com.aliayali.personalfinanceapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.aliayali.personalfinanceapp.data.local.database.entity.AccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(account: AccountEntity)

    @Update
    suspend fun update(account: AccountEntity)

    @Delete
    suspend fun delete(account: AccountEntity)

    @Query("SELECT * FROM account_table WHERE id = :id")
    suspend fun getById(id: Long): AccountEntity?

    @Query("UPDATE account_table SET currentBalance = currentBalance + :amount WHERE id = :accountId")
    suspend fun updateBalance(accountId: Long, amount: Long)

    @Query("SELECT SUM(currentBalance) FROM account_table")
    fun getTotalBalance(): Flow<Long?>

    @Query("SELECT * FROM account_table ORDER BY createdAt DESC")
    fun getAllAccounts(): Flow<List<AccountEntity>>
}