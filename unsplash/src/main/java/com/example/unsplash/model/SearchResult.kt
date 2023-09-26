package com.example.unsplash.model

data class SearchResult<T>(
    val total: String,
    val total_pages: Int,
    val results: List<T>
)