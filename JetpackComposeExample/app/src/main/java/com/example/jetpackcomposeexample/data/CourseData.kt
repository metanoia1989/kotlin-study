package com.example.jetpackcomposeexample.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.jetpackcomposeexample.R

object CourseData {
    val topics = listOf(
        Topic(R.string.architecture, 58, R.drawable.topic_architecture),
        Topic(R.string.crafts, 121, R.drawable.topic_crafts),
        Topic(R.string.business, 78, R.drawable.topic_business),
        Topic(R.string.culinary, 118, R.drawable.topic_culinary),
        Topic(R.string.design, 423, R.drawable.topic_design),
        Topic(R.string.fashion, 92, R.drawable.topic_fashion),
        Topic(R.string.film, 165, R.drawable.topic_film),
        Topic(R.string.gaming, 164, R.drawable.topic_gaming),
        Topic(R.string.drawing, 326, R.drawable.topic_drawing),
        Topic(R.string.lifestyle, 305, R.drawable.topic_lifestyle),
        Topic(R.string.music, 212, R.drawable.topic_music),
        Topic(R.string.painting, 172, R.drawable.topic_painting),
        Topic(R.string.photography, 321, R.drawable.topic_photography),
        Topic(R.string.tech, 118, R.drawable.topic_tech)
    )
}

data class Topic(
    @StringRes val title: Int,
    val associatedCoursesNumber: Int,
    @DrawableRes val cover: Int
)