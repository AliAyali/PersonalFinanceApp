package com.aliayali.personalfinanceapp.presentation.screens.onboardingFeatureB

data class OnboardingFeatureBUiState(
    val dailyStatus: Boolean = true,
    val hour: Int = 21,
    val minute: Int = 0,
    val selectedTime: String = "21:00"
)