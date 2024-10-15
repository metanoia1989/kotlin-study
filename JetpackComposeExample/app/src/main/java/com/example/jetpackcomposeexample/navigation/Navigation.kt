package com.example.jetpackcomposeexample.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposeexample.screens.*

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainScreen(navController) }
        composable("basic_compose") { BasicComposeScreen() }
        composable("dice_roller") { DiceRollerScreen() }
        composable("lemonade") { LemonadeScreen() }
        composable("calculate_tip") { CalculateTipScreen() }
        composable("art_space") { ArtSpaceScreen() }
        // 添加更多页面...
    }
}


