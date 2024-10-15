package com.example.jetpackcomposeexample.data

import androidx.annotation.DrawableRes
import com.example.jetpackcomposeexample.R

data class ArtWork(val title: String, val author: String, @DrawableRes val img: Int)

object ArtSpaceData {
    val ArtListSample = listOf(
        ArtWork(
            title = "Still Life of Blue Rose and Other Flowers",
            author = "Owen Scott (2021)",
            img = R.drawable.bird1
        ),
        ArtWork(
            title = "Sunset Over the Mountains",
            author = "Emily Johnson (1949)",
            img = R.drawable.bird2
        ),
        ArtWork(
            title = "Abstract Dreams",
            author = "Liam Brown (1912)",
            img = R.drawable.bird3
        ),
        ArtWork(
            title = "The Calm Lake",
            author = "Sophia Williams (2004)",
            img = R.drawable.bird4
        ),
        ArtWork(
            title = "City Lights",
            author = "James Smith (1199)",
            img = R.drawable.bird5
        ),
        ArtWork(
            title = "Forest Path",
            author = "Ava Martinez (1344)",
            img = R.drawable.bird6
        ),
        ArtWork(
            title = "Ocean Waves",
            author = "Lucas Anderson (1255)",
            img = R.drawable.bird7
        )
    )
}
