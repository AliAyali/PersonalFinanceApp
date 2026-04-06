package com.aliayali.personalfinanceapp.presentation.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliayali.personalfinanceapp.data.local.database.entity.AccountEntity
import com.aliayali.personalfinanceapp.domain.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
) : ViewModel() {
    private val _account = mutableStateOf<AccountEntity?>(null)
    val account: State<AccountEntity?> = _account
    fun getAllAccounts() = accountRepository.getAllAccounts()
    fun getById(id: Long) {
        viewModelScope.launch {
            _account.value = accountRepository.getById(id)
        }
    }

    suspend fun insert(account: AccountEntity) {
        accountRepository.insert(account)
    }

    suspend fun update(account: AccountEntity) {
        accountRepository.update(account)
    }

    suspend fun delete(account: AccountEntity) {
        accountRepository.delete(account)
    }
}