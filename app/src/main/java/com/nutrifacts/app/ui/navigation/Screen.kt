package com.nutrifacts.app.ui.navigation

sealed class Screen(val route: String) {
    object Landing : Screen("Landing")
    object Login : Screen("Login")
    object Signup : Screen("Signup")
    object Home : Screen("Home")
    object Search : Screen("Search")
    object History : Screen("History")
    object Scanner : Screen("Scanner")
    object Detail : Screen("Home/{barcode}")
    object Profile : Screen("Profile")
    object Settings : Screen("Settings")
}
