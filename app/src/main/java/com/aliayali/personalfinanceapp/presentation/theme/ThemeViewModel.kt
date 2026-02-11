package com.aliayali.personalfinanceapp.presentation.theme

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliayali.personalfinanceapp.data.local.datastore.ThemeDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val themeDataStore: ThemeDataStore,
) : ViewModel() {

    var isDarkTheme = mutableStateOf(false)
        private set

    init {
        viewModelScope.launch {
            themeDataStore.isDarkThemeFlow.collect {
                isDarkTheme.value = it
            }
        }
    }

    fun setDarkTheme(enabled: Boolean) {
        isDarkTheme.value = enabled

        viewModelScope.launch {
            themeDataStore.saveTheme(enabled)
        }
    }
}