package com.aliayali.personalfinanceapp.navigation

import com.aliayali.personalfinanceapp.R

sealed class BottomNavigationScreen(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Home : BottomNavigationScreen(
        "home",
        "خانه",
        R.drawable.ic_home
    )
    object Property : BottomNavigationScreen(
        "property",
        "دارایی",
        R.drawable.ic_property
    )
    object Reporting : BottomNavigationScreen(
        "reporting",
        "گزارش",
        R.drawable.ic_reporting
    )
    object Budget : BottomNavigationScreen(
        "budget",
        "بودجه",
        R.drawable.ic_budget
    )
    object Due : BottomNavigationScreen(
        "due",
        "سررسید",
        R.drawable.ic_due
    )
    companion object {
        val btnNavItems = listOf(Home, Property, Reporting, Budget, Due)
    }
}