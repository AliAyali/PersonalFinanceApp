package com.aliayali.personalfinanceapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aliayali.personalfinanceapp.presentation.screens.budget.BudgetScreen
import com.aliayali.personalfinanceapp.presentation.screens.due.DueScreen
import com.aliayali.personalfinanceapp.presentation.screens.home.HomeScreen
import com.aliayali.personalfinanceapp.presentation.screens.property.PropertyScreen
import com.aliayali.personalfinanceapp.presentation.screens.reporting.ReportingScreen
import com.aliayali.personalfinanceapp.presentation.screens.theme.ThemeViewModel
import com.aliayali.personalfinanceapp.ui.theme.PersonalFinanceAppTheme

@Composable
fun SetupBottomNavigation(
    navController: NavHostController,
    paddingValues: PaddingValues,
) {
    val themeViewModel: ThemeViewModel = hiltViewModel()
    val isDarkTheme = themeViewModel.isDarkTheme.value
    PersonalFinanceAppTheme(
        darkTheme = isDarkTheme
    ) {
        NavHost(
            navController = navController,
            startDestination = BottomNavigationScreen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(BottomNavigationScreen.Home.route) {
                HomeScreen(navController)
            }
            composable(BottomNavigationScreen.Property.route) {
                PropertyScreen(navController)
            }
            composable(BottomNavigationScreen.Reporting.route) {
                ReportingScreen(navController)
            }
            composable(BottomNavigationScreen.Budget.route) {
                BudgetScreen(navController)
            }
            composable(BottomNavigationScreen.Due.route) {
                DueScreen(navController)
            }
        }
    }
}