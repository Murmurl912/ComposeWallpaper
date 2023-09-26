package com.example.wallpaper

interface WallpaperService {

    suspend fun wallpapers(category: WallpaperCategory? = null, page: Int?, perPage: Int?): List<WallpaperPhoto>

    suspend fun search(
        query: String,
        color: Int?,
        orientation: PhotoOrientation? = null,
        orderBy: PhotoOrder? = null,
        page: Int? = null,
        perPage: Int? = null
    )

    suspend fun categories(page: Int?, perPage: Int?): List<WallpaperCategory>

    suspend fun setWallpaper(photo: WallpaperPhoto, screen: WallpaperScreen)

    suspend fun favorite(photo: WallpaperPhoto)

    suspend fun unfavorite(photo: WallpaperPhoto)

    suspend fun favorites(page: Int?, perPage: Int?): List<WallpaperPhoto>

    suspend fun recent(page: Int?, perPage: Int?): List<WallpaperPhoto>

    enum class PhotoOrientation {
        Landscape,
        Portrait,
        Squarish
    }

    enum class PhotoOrder {
        Latest,
        Relevant
    }

    enum class WallpaperScreen {
        LockScreen,
        HomeScreen,
        Both
    }


}