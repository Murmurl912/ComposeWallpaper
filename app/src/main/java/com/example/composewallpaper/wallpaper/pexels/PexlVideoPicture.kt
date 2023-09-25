package com.example.composewallpaper.wallpaper.pexels

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PexlVideoPicture(
    val id: Int,
    val picture: String,
    val nr: Int
)