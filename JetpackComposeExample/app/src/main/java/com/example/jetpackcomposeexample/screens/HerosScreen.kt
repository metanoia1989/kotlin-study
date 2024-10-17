package com.example.jetpackcomposeexample.screens

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.StiffnessLow
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeexample.R
import com.example.jetpackcomposeexample.data.HerosRepository
import com.example.jetpackcomposeexample.model.Hero
import com.example.jetpackcomposeexample.ui.theme.JetpackComposeExampleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HerosScreen(animateEntrance: Boolean = true, modifier: Modifier = Modifier) {

    val visibleState = remember {
        MutableTransitionState(!animateEntrance).apply {
            targetState = true
        }
    }

    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = DampingRatioLowBouncy)
        ),
        exit = fadeOut(),
        modifier = modifier
    ) {
        Scaffold (
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = stringResource(R.string.hero_app_name),
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }
                )
            },
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) { innerPadding ->
            LazyColumn(
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(innerPadding)
            ) {
                itemsIndexed(HerosRepository.heros) { index, hero ->
                    HeroItem(
                        hero = hero,
                        modifier = Modifier
                            .animateEnterExit(
                                enter = slideInVertically(
                                    animationSpec = spring(
                                        stiffness = StiffnessLow,
                                        dampingRatio = DampingRatioLowBouncy,
                                    ),
                                    initialOffsetY = { it * (index + 1) }
                                )
                            )
                    )
                }
            }
        }

    }

}

@Preview
@Composable
fun HerosScreenPreview() {
    HerosScreen(animateEntrance = false)
}

@Preview
@Composable
fun HerosScreenDarkPreview() {
    JetpackComposeExampleTheme(darkTheme = true) {
        HerosScreen(animateEntrance = false)
    }
}


@Composable
fun HeroItem(hero: Hero, modifier: Modifier = Modifier) {

    // 在 Material 3 中，Card 默认不再有阴影效果。要添加阴影效果，
    // 我们需要使用 shape 参数并结合 shadowElevation 参数。
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
//            .background(MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(72.dp)
        ) {
            HeroInformation(
                hero.nameRes,
                hero.descriptionRes,
                modifier = Modifier.weight(1f)
            )

            Spacer(Modifier.width(16.dp))

            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Image(
                    painter = painterResource(hero.imageRes),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )
            }
        }
    }
}

@Composable
fun HeroInformation(
    @StringRes heroName: Int,
    @StringRes heroDescription: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(heroName),
            style = MaterialTheme.typography.titleSmall,
        )
        Text(
            text = stringResource(heroDescription),
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}