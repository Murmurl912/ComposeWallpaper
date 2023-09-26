package com.example.pexels.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PexelVideo(
    val id: Int,
    val width: Int,
    val height: Int,
    val url: String,
    val image: String,
    val duration: Int,
    val user: PexelUser,
    val video_files: List<PexelVideoFile>,
    val video_pictures: List<PexlVideoPicture>,
    val type: String = "video",
    ): PexelMedia