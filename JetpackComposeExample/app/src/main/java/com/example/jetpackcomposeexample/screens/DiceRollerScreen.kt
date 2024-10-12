package com.example.jetpackcomposeexample.screens

import android.util.Log
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeexample.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun DiceRollerScreen() {
    CommonScreen(title = "掷骰子游戏") {
        DiceWithButtonAndImage()
    }
}



@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier
    .fillMaxSize()
    .wrapContentSize(Alignment.Center)
)
{
    var result by remember { mutableStateOf(1) }
    var isRolling by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val animatedFace by animateIntAsState(
        targetValue = result,
        animationSpec = if (isRolling) infiniteRepeatable(
            animation = tween(durationMillis = 100),
            repeatMode = RepeatMode.Restart
        ) else tween(durationMillis = 300),
    )
    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = animatedFace.toString(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (isRolling) {
                return@Button
            }

            isRolling = true
            scope.launch {
                repeat(30) {
                    result = (1..6).random()
                    delay(100)
                }
                isRolling = false
            }
        }) {
            Text(stringResource(R.string.roll))
        }
    }
}


@Preview
@Composable
private fun DiceRollerPreivew() {
    Surface {
        DiceRollerScreen()
    }
}
