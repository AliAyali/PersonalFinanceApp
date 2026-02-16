package com.aliayali.personalfinanceapp.presentation.screens.onboardingFeatureB

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliayali.personalfinanceapp.data.local.datastore.NotificationDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingFeatureBViewModel @Inject constructor(
    private val notificationDataStore: NotificationDataStore,
) : ViewModel() {

    var dailyStatus by mutableStateOf(true)
        private set

    init {
        viewModelScope.launch {
            notificationDataStore.dailyStatusFlow.collect { enabled ->
                dailyStatus = enabled
            }
        }
    }

    fun setDailyStatus(enabled: Boolean) {
        dailyStatus = enabled
        viewModelScope.launch {
            notificationDataStore.saveDailyStatus(enabled)
        }
    }

    var selectedTime by mutableStateOf("21:00")
        private set

    private var selectedHour = 21
    private var selectedMinute = 0

    init {
        viewModelScope.launch {
            notificationDataStore.notificationTimeFlow.collect { (hour, minute) ->
                selectedHour = hour
                selectedMinute = minute
                updateSelectedTime()
            }
        }
    }


    fun onTimeSelected(hour: Int, minute: Int) {
        selectedHour = hour
        selectedMinute = minute
        updateSelectedTime()

        viewModelScope.launch {
            notificationDataStore.saveNotificationTime(hour, minute)
        }
    }

    private fun updateSelectedTime() {
        selectedTime = "$selectedHour:$selectedMinute"
    }

    fun getSelectedTime(): Pair<Int, Int> {
        return selectedHour to selectedMinute
    }
}
