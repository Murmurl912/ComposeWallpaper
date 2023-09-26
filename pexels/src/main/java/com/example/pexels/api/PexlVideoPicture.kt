package com.example.pexels.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PexlVideoPicture(
    val id: Int,
    val picture: String,
    val nr: Int
)