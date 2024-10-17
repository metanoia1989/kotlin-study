package com.example.jetpackcomposeexample.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Grain
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableTargetMarker
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeexample.data.CourseData
import com.example.jetpackcomposeexample.data.Topic
import com.example.jetpackcomposeexample.ui.theme.JetpackComposeExampleTheme
import com.example.jetpackcomposeexample.ui.theme.Shapes
import com.example.jetpackcomposeexample.ui.theme.backgroundLight
import com.example.jetpackcomposeexample.ui.theme.onBackgroundLight

@Composable
fun TopicsScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(CourseData.topics) { topic ->
                TopicItem(topic)
            }
        }
    }

}

@Preview
@Composable
fun TopicsScreenPreview() {
    TopicsScreen()
}

@Preview
@Composable
fun TopicsScreenDarkPreview() {
    JetpackComposeExampleTheme(darkTheme = true) {
        TopicsScreen()
    }
}


@Composable
fun TopicItem(topic: Topic, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .height(68.dp)
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Image(
            painter = painterResource(topic.cover),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .aspectRatio(1f) // 强制组件保持 1:1 的宽高比
//                .width(68.dp)
//                .height(68.dp)
        )

        TopicInformation(
            title =  topic.title,
            associatedCoursesNumber = topic.associatedCoursesNumber,
        )
    }
}

@Composable
fun TopicInformation(
    @StringRes title: Int,
    associatedCoursesNumber: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.bodyMedium,
        )

        Spacer(Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically

        ) {
            Icon(
                imageVector = Icons.Filled.Grain,
                tint =MaterialTheme.colorScheme.secondary,
                contentDescription = null,
                modifier = Modifier.padding(2.dp)
            )

            Spacer(Modifier.width(8.dp))

            Text(
                text = associatedCoursesNumber.toString(),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}