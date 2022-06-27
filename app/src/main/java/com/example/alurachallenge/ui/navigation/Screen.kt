package com.example.alurachallenge.ui.navigation

sealed class Screen(val route: String) {
    object Account : Screen(route = "account_screen")
    object Profile : Screen(route = "profile_screen")
}
