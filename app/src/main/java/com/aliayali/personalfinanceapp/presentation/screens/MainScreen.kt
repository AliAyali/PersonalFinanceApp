package com.aliayali.personalfinanceapp.presentation.screens

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.aliayali.personalfinanceapp.navigation.BottomNavigationBar
import com.aliayali.personalfinanceapp.navigation.SetupBottomNavigation

@Composable
fun MainScreen() {
    val bottomNavController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(bottomNavController) }
    ) { paddingValues ->
        SetupBottomNavigation(
            navController = bottomNavController,
            paddingValues = paddingValues
        )
    }
}