package com.example.composewallpaper.wallpaper.pexels

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PexelUser(
    val id: Int,
    val name: String,
    val url: String
)
