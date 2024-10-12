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
        composable("text") { TextScreen() }
        composable("button") { ButtonScreen() }
        composable("image") { ImageScreen() }
        composable("list") { ListScreen() }
        composable("text2") { Text2Screen() }
        // 添加更多页面...
    }
}


