package com.example.pexels.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PexelPhoto(
    val id: Int,
    val width: Int,
    val height: Int,
    val url: String,
    val photographer: String,
    val photographer_url: String,
    val avg_color: String,
    val src: PexelPhotoSrc,
    val liked: Boolean,
    val alt: String?,
    val type: String = "photo"
): PexelMedia