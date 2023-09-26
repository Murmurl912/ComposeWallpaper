package com.example.wallpaper

interface WallpaperProvider {

    suspend fun wallpapers(
        category: WallpaperCategory?,
        page: Int?,
        perPage: Int?): List<WallpaperPhoto>

    suspend fun categories(page: Int?, perPage: Int?): List<WallpaperCategory>

    suspend fun search(
        query: String,
        color: Int?,
        orientation: WallpaperService.PhotoOrientation? = null,
        orderBy: WallpaperService.PhotoOrder? = null,
        page: Int? = null,
        perPage: Int? = null
    ): List<WallpaperPhoto>
}