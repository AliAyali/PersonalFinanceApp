package com.aliayali.personalfinanceapp.navigation

sealed class NavigationScreen(val route: String) {
    object Splash : NavigationScreen("splash")
    object OnboardingWelcome : NavigationScreen("onboardingWelcome")
    object OnboardingFeatureA : NavigationScreen("onboardingFeatureA")
}