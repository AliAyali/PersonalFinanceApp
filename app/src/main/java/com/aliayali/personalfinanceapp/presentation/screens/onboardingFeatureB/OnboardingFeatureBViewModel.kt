package com.aliayali.personalfinanceapp.presentation.screens.onboardingFeatureB

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingFeatureBViewModel @Inject constructor() : ViewModel() {
    var dailyStatus by mutableStateOf(true)
        private set

    var selectedTime by mutableStateOf("21:00")
        private set

    fun onDailyStatusChange(value: Boolean) {
        dailyStatus = value
    }

    fun onTimeSelected(hour: Int, minute: Int) {
        selectedTime = "$hour:$minute"
    }
}