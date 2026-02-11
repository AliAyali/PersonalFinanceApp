package com.aliayali.personalfinanceapp.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliayali.personalfinanceapp.data.local.datastore.SplashPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    splashPreferences: SplashPreferences,
) : ViewModel() {
    private val _shouldShowSplash: StateFlow<Boolean> = splashPreferences.showSplash
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = true
        )
    private val _isDelayFinished = MutableStateFlow(false)

    val uiState: StateFlow<SplashUiState> = combine(
        _shouldShowSplash,
        _isDelayFinished
    ) { shouldShow, delayFinished ->
        SplashUiState(
            isSplashShown = shouldShow,
            isDelayFinished = delayFinished
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SplashUiState(
            isSplashShown = true,
            isDelayFinished = false
        )
    )

    init {
        startSplashDelay()
    }

    private fun startSplashDelay() {
        viewModelScope.launch {
            delay(2000L)
            _isDelayFinished.value = true
        }
    }
}
