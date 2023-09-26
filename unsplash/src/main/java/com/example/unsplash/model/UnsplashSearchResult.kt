package com.example.unsplash.model

data class UnsplashSearchResult<T>(
    val total: String,
    val total_pages: Int,
    val results: List<T>
)