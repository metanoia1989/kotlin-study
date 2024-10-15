package com.example.jetpackcomposeexample.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposeexample.R
import com.example.jetpackcomposeexample.data.ArtSpaceData

@Composable
fun ArtSpaceScreen() {
    val layoutDirection = LocalLayoutDirection.current

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateEndPadding(layoutDirection)
            )
    ) {
        ArtSpace()
    }
}

@Preview
@Composable
fun ArtSpaceScreenPreview() {
    Surface {
        ArtSpaceScreen()
    }
}


@Composable
fun ArtSpace() {
    var currIndex by remember { mutableStateOf(0) }
    val artWork = ArtSpaceData.ArtListSample.get(currIndex)
    val artTotal = ArtSpaceData.ArtListSample.size

    val onPreviousClick = {
        currIndex = if (currIndex > 1) currIndex - 1  else 0
    }
    val onNextClick = {
        currIndex = if (currIndex + 1 < artTotal) currIndex + 1 else currIndex
    }

    // 页面左侧两侧保持 20.dp 的padding
    val modifier = Modifier.padding(start = 20.dp, end = 20.dp)

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())

    ) {
        ArtworkWall(
            artWork.img,
            modifier = modifier.padding(top = 35.dp)
        )

        Column (
            modifier = modifier.padding(top = 40.dp, bottom = 20.dp)
        ) {
            ArtworkDescriptor(
                artTitle = artWork.title,
                artAuthor = artWork.author,
            )

            Spacer(modifier = Modifier.height(20.dp))

            ArtworkControl(
                onPreviousClick = onPreviousClick,
                onNextClick = onNextClick
            )
        }
    }
}

@Composable
fun ArtworkWall(
    @DrawableRes artImg: Int,
    modifier: Modifier = Modifier
) {
    Surface (
        shadowElevation = 8.dp,
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(artImg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.padding(25.dp)
        )
    }
}

@Composable
fun ArtworkDescriptor(
    artTitle: String,
    artAuthor: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.background(Color(0xffEDEBF3))
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Text(
                text = artTitle,
                fontSize = 20.sp,
                fontWeight = FontWeight.W300
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(text = artAuthor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ArtworkControl(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
)
{
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Button(
            onClick = onPreviousClick,
            modifier = Modifier.weight(1f)
                .padding(end = 15.dp)
        ) {
            Text(text = stringResource(R.string.previous))
        }
        Button(
            onClick = onNextClick,
            modifier = Modifier.weight(1f)
                .padding(start = 15.dp)
        ) {
            Text(text = stringResource(R.string.next))
        }
    }
}