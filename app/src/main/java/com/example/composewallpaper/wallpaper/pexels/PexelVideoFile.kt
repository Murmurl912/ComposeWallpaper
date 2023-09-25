package com.example.composewallpaper.wallpaper.pexels

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PexelVideoFile(
    val id: Int,
    val quality: String,
    val file_type: String,
    val width: Int,
    val height: Int,
    val fps: Float,
    val link: String,
)
