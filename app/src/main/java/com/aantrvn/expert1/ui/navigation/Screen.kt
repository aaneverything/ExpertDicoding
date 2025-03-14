package com.aantrvn.expert1.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object profile : Screen("profile")
    object Favorite : Screen("favorite")
}