package com.nutrifacts.app.ui.navigation

sealed class Screen(val route: String) {
    object Landing : Screen("landing")
    object Login : Screen("login")
    object Signup : Screen("signup")
    object Home : Screen("home")
    object Search : Screen("search")
    object History : Screen("history")
    object Scanner : Screen("scanner")
    object Detail : Screen("home/{barcode}")
    object Profile : Screen("profile")
    object Settings : Screen("settings")
}
