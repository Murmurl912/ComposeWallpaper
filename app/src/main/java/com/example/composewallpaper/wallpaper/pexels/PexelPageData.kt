package com.example.composewallpaper.wallpaper.pexels

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PexelVideoPageData(
    val total_results: Int,
    val page: Int,
    val per_page: Int,
    val videos: List<PexelVideo>,
    val next_page: String?,
    val prev_page: String?
)

@JsonClass(generateAdapter = true)
data class PexelPhotoPageData(
    val total_results: Int,
    val page: Int,
    val per_page: Int,
    val photos: List<PexelPhoto>,
    val next_page: String?,
    val prev_page: String?
)

@JsonClass(generateAdapter = true)
data class PexelMediaPageData(
    val total_results: Int,
    val page: Int,
    val per_page: Int,
    val meida: List<PexelMedia>,
    val next_page: String?,
    val prev_page: String?
)

@JsonClass(generateAdapter = true)
data class PexelCollectionPageData(
    val total_results: Int,
    val page: Int,
    val per_page: Int,
    val collection: List<PexelCollection>,
    val next_page: String?,
    val prev_page: String?
)