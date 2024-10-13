package com.example.jetpackcomposeexample.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposeexample.R

@Composable
fun LemonadeScreen() {
    CommonScreen(title = "柠檬水制作") {
        LemonadeSteps()
    }
}


@Composable
fun LemonadeSteps(modifier: Modifier = Modifier) {
    var step by rememberSaveable { mutableStateOf(1) }

    val imageResource = when(step) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val imgDesc = when(step) {
        1 -> R.string.lemon_tree_img_desc
        2 -> R.string.lemon_img_desc
        3 -> R.string.glass_img_desc
        else -> R.string.empty_img_desc
    }

    val textResource = when(step) {
        1 -> R.string.tap_lemon_tree
        2 -> R.string.keep_tapping_lemon
        3 -> R.string.drink_lemonade
        else -> R.string.tap_empty_glass
    }

    var tapSqueezeTimes by remember { mutableStateOf(0) } // 使用 remember 来保持状态
    var squeezeTimes by remember { mutableStateOf((2..4).random()) } // 使用 remember 来保持状态


    Surface(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column (
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 另一种方式是用按钮包裹图片
            Image(
                painter = painterResource(imageResource),
                contentDescription = stringResource(imgDesc),
                modifier = Modifier.clickable {
                    if (step == 2 && tapSqueezeTimes < squeezeTimes) {
                        tapSqueezeTimes++
                        return@clickable
                    }
                    if (tapSqueezeTimes == squeezeTimes) {
                        squeezeTimes = (2..4).random()
                        tapSqueezeTimes = 0
                    }

                    step = step % 4 + 1
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = stringResource(textResource), fontSize = 18.sp)
        }

    }
}

@Preview
@Composable
fun LemonadeScreenPreview() {
    Surface {
        LemonadeScreen()
    }
}