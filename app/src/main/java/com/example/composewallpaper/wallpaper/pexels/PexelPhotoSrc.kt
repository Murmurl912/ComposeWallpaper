package com.example.composewallpaper.wallpaper.pexels

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PexelPhotoSrc(
    val original: String,
    val large2x: String,
    val large: String,
    val medium: String,
    val small: String,
    val portrait: String,
    val landscape: String,
    val tiny: String,
)