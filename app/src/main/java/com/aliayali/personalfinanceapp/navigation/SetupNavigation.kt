package com.aliayali.personalfinanceapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aliayali.personalfinanceapp.presentation.theme.ThemeViewModel
import com.aliayali.personalfinanceapp.presentation.screens.onboardingFeatureA.OnboardingFeatureAScreen
import com.aliayali.personalfinanceapp.presentation.screens.onboardingFeatureB.OnboardingFeatureBScreen
import com.aliayali.personalfinanceapp.presentation.screens.onboardingWelcome.OnboardingWelcomeScreen
import com.aliayali.personalfinanceapp.presentation.screens.splash.SplashScreen
import com.aliayali.personalfinanceapp.ui.theme.PersonalFinanceAppTheme

@Composable
fun SetupNavigation(
    padding: PaddingValues,
    navController: NavHostController,
) {
    val themeViewModel: ThemeViewModel = hiltViewModel()
    val isDarkTheme = themeViewModel.isDarkTheme.value
    PersonalFinanceAppTheme(
        darkTheme = isDarkTheme
    ) {
        NavHost(
            navController = navController,
            startDestination = NavigationScreen.Splash.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(
                route = NavigationScreen.Splash.route
            ) {
                SplashScreen(navController)
            }
            composable(
                route = NavigationScreen.OnboardingWelcome.route
            ) {
                OnboardingWelcomeScreen(navController)
            }
            composable(
                route = NavigationScreen.OnboardingFeatureA.route
            ) {
                OnboardingFeatureAScreen(
                    navController,
                    themeViewModel
                )
            }
            composable(
                route = NavigationScreen.OnboardingFeatureB.route
            ) {
                OnboardingFeatureBScreen(navController)
            }
        }
    }
}