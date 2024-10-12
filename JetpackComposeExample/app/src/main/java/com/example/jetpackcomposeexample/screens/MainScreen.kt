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

        "按钮组件" to "button",
        "图片组件" to "image",
        "列表组件" to "list",
        // 添加更多项目...
        "文本组件2" to "text2"
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