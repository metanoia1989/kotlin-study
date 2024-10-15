package com.example.jetpackcomposeexample.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun MainScreen(navController: NavHostController) {
    val items = listOf(
        "基础compose组件效果演示" to "basic_compose",
        "掷骰子游戏" to "dice_roller",
        "柠檬水制作" to "lemonade",
        "小费计算器" to "calculate_tip",
        "艺术空间" to "art_space",
        "自我肯定集" to "affirmation",
    )

    LazyColumn {
        items(items) { (title, route) ->
            ListItem(
                headlineContent = { Text(title) },
                modifier = Modifier
                    .clickable { navController.navigate(route) }
                    .padding(16.dp)
            )
            HorizontalDivider()
        }
    }
}