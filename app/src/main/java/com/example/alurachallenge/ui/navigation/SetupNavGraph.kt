package com.example.alurachallenge.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.alurachallenge.ui.screen.AccountScreen
import com.example.alurachallenge.ui.screen.ProfileScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Account.route) {
        composable(route = Screen.Account.route) {
            AccountScreen(navController)
        }
        composable(route = Screen.Profile.route + "/{username}") { backStackEntry ->
            backStackEntry.arguments?.getString("username")?.let { ProfileScreen(username = it) }
        }

    }
}