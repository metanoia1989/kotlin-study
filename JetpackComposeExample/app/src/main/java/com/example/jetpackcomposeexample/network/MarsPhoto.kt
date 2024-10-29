package com.example.jetpackcomposeexample.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarsPhoto(
    val id: String,
    @SerialName(value = "img_src") val imgSrc: String
) {
    override fun toString(): String {
        return "MarsPhoto(id=$id, imgSrc=$imgSrc)"
    }
}