package com.aliayali.personalfinanceapp.presentation.screens.onboardingFeatureB

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliayali.personalfinanceapp.data.local.datastore.NotificationDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingFeatureBViewModel @Inject constructor(
    private val notificationDataStore: NotificationDataStore,
) : ViewModel() {

    private val _uiState = MutableStateFlow(OnboardingFeatureBUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            notificationDataStore.dailyStatusFlow.collect { enabled ->
                _uiState.update { it.copy(dailyStatus = enabled) }
            }
        }

        viewModelScope.launch {
            notificationDataStore.notificationTimeFlow.collect { (hour, minute) ->
                val timeStr = "$hour:$minute"
                _uiState.update { it.copy(hour = hour, minute = minute, selectedTime = timeStr) }
            }
        }
    }

    fun setDailyStatus(enabled: Boolean) {
        viewModelScope.launch {
            notificationDataStore.saveDailyStatus(enabled)
        }
        _uiState.update { it.copy(dailyStatus = enabled) }
    }

    fun onTimeSelected(hour: Int, minute: Int) {
        viewModelScope.launch {
            notificationDataStore.saveNotificationTime(hour, minute)
        }
        val timeStr = "$hour:$minute"
        _uiState.update { it.copy(hour = hour, minute = minute, selectedTime = timeStr) }
    }

    fun getSelectedTime(): Pair<Int, Int> {
        val state = _uiState.value
        return state.hour to state.minute
    }
}
