package com.aliayali.personalfinanceapp.presentation.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliayali.personalfinanceapp.core.util.PersianDate
import com.aliayali.personalfinanceapp.data.local.database.entity.AccountEntity
import com.aliayali.personalfinanceapp.data.local.database.entity.NoteEntity
import com.aliayali.personalfinanceapp.domain.repository.AccountRepository
import com.aliayali.personalfinanceapp.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val noteRepository: NoteRepository,
) : ViewModel() {
    private val _account = mutableStateOf<AccountEntity?>(null)
    val account: State<AccountEntity?> = _account
    private val _note = mutableStateOf<NoteEntity?>(null)
    val note: State<NoteEntity?> = _note
    private var _date = mutableStateOf<String>("")
    val date: State<String> = _date

    init {
        _date.value = PersianDate().getTodayPersianDate()
    }

    fun getAllAccounts() = accountRepository.getAllAccounts()
    fun getNotesByDate() = noteRepository.getNotesByDate(_date.value)
    fun getAllNotes() = noteRepository.getAllNotes()
    fun getAccountById(id: Long) {
        viewModelScope.launch {
            _account.value = accountRepository.getById(id)
        }
    }

    fun getNoteById(id: Long) {
        viewModelScope.launch {
            _note.value = noteRepository.getNoteById(id)
        }
    }

    suspend fun insertAccount(account: AccountEntity) {
        accountRepository.insert(account)
    }

    suspend fun updateAccount(account: AccountEntity) {
        accountRepository.update(account)
    }

    suspend fun deleteAccount(account: AccountEntity) {
        accountRepository.delete(account)
    }

    suspend fun insertNote(note: NoteEntity) {
        noteRepository.insertNote(note)
    }

    suspend fun deleteNote(note: NoteEntity) {
        noteRepository.deleteNote(note)
    }

    suspend fun updateNote(note: NoteEntity) {
        noteRepository.updateNote(note)
    }
}