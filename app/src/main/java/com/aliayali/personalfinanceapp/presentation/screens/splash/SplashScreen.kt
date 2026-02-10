package com.aliayali.personalfinanceapp.presentation.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aliayali.personalfinanceapp.R
import com.aliayali.personalfinanceapp.navigation.NavigationScreen

@Composable
fun SplashScreen(
    navController: NavController,
    splashViewModel: SplashViewModel = hiltViewModel(),
) {
    val splashUiState by splashViewModel.uiState.collectAsState()

    LaunchedEffect(splashUiState.isSplashShown) {
        if (splashUiState.isSplashShown) {
            navController.navigate(NavigationScreen.OnboardingWelcome.route) {
                popUpTo(NavigationScreen.Splash.route) { inclusive = true }
                launchSingleTop = true
            }
        }
    }
    if (splashUiState.isDelayFinished) //navigate to home

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(id = R.drawable.ic_application),
                contentDescription = null
            )

            //Check Internet
        }

}