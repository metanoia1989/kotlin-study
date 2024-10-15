package com.example.jetpackcomposeexample

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.example.jetpackcomposeexample.screens.CalculateTipScreen
import com.example.jetpackcomposeexample.ui.theme.JetpackComposeExampleTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

class CalculateTipUITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip() {
        // 设置 UI
        composeTestRule.setContent {
            JetpackComposeExampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculateTipScreen()
                }
            }
        }

        // 测试节点文本
        composeTestRule.onNodeWithText("Bill Amount").performTextInput("10") // 填充数值
        composeTestRule.onNodeWithText("Tip Percentage").performTextInput("20")

        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        composeTestRule.onNodeWithText("Tip Amount: $expectedTip")
            .assertExists("No node with this text was found")
    }
}