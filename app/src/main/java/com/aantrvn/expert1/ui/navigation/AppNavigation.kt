package com.aantrvn.expert1.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = Screen.Home.route, modifier) {
        composable(Screen.Home.route) { }
        composable(Screen.Favorite.route) { }
        composable(Screen.profile.route) { }
    }
}