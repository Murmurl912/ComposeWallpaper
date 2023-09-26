package com.example.wallpaper

interface WallpaperService {

    suspend fun wallpapers(): List<WallpaperPhoto>

    suspend fun searchWallpapers(
        query: String,
        color: Int?,
        orientation: PhotoOrientation? = null,
        orderBy: PhotoOrder? = null,
        page: Int? = null,
        perPage: Int? = null
    )

    suspend fun collections(page: Int?, perPage: Int?): List<WallpaperCollection>

    suspend fun setWallpaper(photo: WallpaperPhoto, screen: WallpaperScreen)

    suspend fun favorite(photo: WallpaperPhoto)

    suspend fun unfavorite(photo: WallpaperPhoto)

    suspend fun favorites(page: Int?, perPage: Int?): List<WallpaperPhoto>

    suspend fun recentWallpapers(): List<WallpaperPhoto>

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