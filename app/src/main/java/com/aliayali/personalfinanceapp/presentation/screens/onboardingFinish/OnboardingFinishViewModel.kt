package com.aliayali.personalfinanceapp.presentation.screens.onboardingFinish

import android.content.Context
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliayali.personalfinanceapp.data.local.database.entity.AccountEntity
import com.aliayali.personalfinanceapp.data.local.database.model.AccountType
import com.aliayali.personalfinanceapp.domain.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingFinishViewModel @Inject constructor(
    @param:ApplicationContext private val context: Context,
    private val accountRepository: AccountRepository,
) : ViewModel() {
    fun createDefaultAccountIfNeeded() {
        val prefs = context.getSharedPreferences("account", Context.MODE_PRIVATE)
        viewModelScope.launch {
            val isCreated = prefs.getBoolean("default_account_created", false)
            if (!isCreated) {
                accountRepository.insert(
                    AccountEntity(
                        name = "نقدی",
                        type = AccountType.OTHER,
                        iconName = "ic_cash",
                        initialBalance = 0L,
                        currentBalance = 0L
                    )
                )
                prefs.edit {
                    putBoolean("default_account_created", true)
                }
            }
        }
    }

    fun getAllAccounts() = accountRepository.getAllAccounts()
}