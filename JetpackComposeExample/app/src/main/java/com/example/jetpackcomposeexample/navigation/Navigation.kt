package com.example.jetpackcomposeexample.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposeexample.screens.AffirmationScreen
import com.example.jetpackcomposeexample.screens.ArtSpaceScreen
import com.example.jetpackcomposeexample.screens.BasicComposeScreen
import com.example.jetpackcomposeexample.screens.CalculateTipScreen
import com.example.jetpackcomposeexample.screens.DiceRollerScreen
import com.example.jetpackcomposeexample.screens.HerosScreen
import com.example.jetpackcomposeexample.screens.LemonadeScreen
import com.example.jetpackcomposeexample.screens.MainScreen
import com.example.jetpackcomposeexample.screens.MarsPhotosScreen
import com.example.jetpackcomposeexample.screens.RaceTrackerScreen
import com.example.jetpackcomposeexample.screens.TopicsScreen
import com.example.jetpackcomposeexample.screens.WoofScreen

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
        composable("affirmation") { AffirmationScreen() }
        composable("woof") { WoofScreen() }
        composable("topics") { TopicsScreen() }
        composable("heros") { HerosScreen() }
        composable("race_tracker") { RaceTrackerScreen() }
        composable("mars_photos") { MarsPhotosScreen() }
    }
}


