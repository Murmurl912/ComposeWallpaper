package com.example.composewallpaper.wallpaper.pexels

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PexelCollection(
    val id: String,
    val title: String,
    val description: String,
    val private: Boolean,
    val media_count: Int,
    val photos_count: Int,
    val videos_count: Int,
)