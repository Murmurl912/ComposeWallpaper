package com.example.unsplash.model

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class UnsplashSearchResult<T>(
    val total: String,
    val total_pages: Int,
    val results: List<T>
)