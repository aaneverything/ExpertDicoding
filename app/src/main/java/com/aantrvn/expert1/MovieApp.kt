package com.aantrvn.expert1

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.aantrvn.expert1.ui.home.HomeScreen
import com.aantrvn.expert1.ui.navigation.AppNavigation
import com.aantrvn.expert1.ui.navigation.components.BottomBar

@Composable
fun MovieApp(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(
                modifier = Modifier
            )
        },
        modifier = modifier
    ) { innerPadding ->
        AppNavigation(
            navController,
            modifier = Modifier.padding(innerPadding),
        )
        HomeScreen(
            navController = navController,
        )
    }
}