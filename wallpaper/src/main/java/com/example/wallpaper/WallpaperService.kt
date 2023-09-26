package com.example.wallpaper

import com.example.unsplash.UnsplashApi
import com.example.wallpaper.impl.WallpaperServiceImpl
import com.example.wallpaper.unsplash.UnsplashWallpaperPhoto
import com.example.wallpaper.unsplash.UnsplashWallpaperProvider

interface WallpaperService {

    suspend fun wallpapers(category: WallpaperCategory? = null,
                           page: Int? = null,
                           perPage: Int? = null): Result<List<WallpaperPhoto>>

    suspend fun search(
        query: String,
        color: Int?,
        orientation: PhotoOrientation? = null,
        orderBy: PhotoOrder? = null,
        page: Int? = null,
        perPage: Int? = null
    ): Result<List<WallpaperPhoto>>

    suspend fun categories(page: Int?, perPage: Int?): Result<List<WallpaperCategory>>

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


    companion object {

        fun create(): WallpaperService {
            return WallpaperServiceImpl(UnsplashWallpaperProvider(
                UnsplashApi.create()
            ))
        }
    }
}