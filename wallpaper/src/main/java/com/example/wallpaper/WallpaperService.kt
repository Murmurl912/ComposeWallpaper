package com.example.wallpaper

import androidx.paging.PagingSource
import com.example.unsplash.UnsplashApi
import com.example.wallpaper.impl.WallpaperServiceImpl
import com.example.wallpaper.unsplash.UnsplashWallpaperPhoto
import com.example.wallpaper.unsplash.UnsplashWallpaperProvider

interface WallpaperService {

    fun wallpapers(category: WallpaperCategory? = null): PagingSource<Int, WallpaperPhoto>

    fun search(
        query: String,
        color: Int?,
        orientation: PhotoOrientation? = null,
        orderBy: PhotoOrder? = null,
    ): PagingSource<Int, WallpaperPhoto>

    fun categories(): PagingSource<Int, WallpaperCategory>

    suspend fun setWallpaper(photo: WallpaperPhoto, screen: WallpaperScreen)

    suspend fun favorite(photo: WallpaperPhoto)

    suspend fun unfavorite(photo: WallpaperPhoto)

    suspend fun favorites(page: Int?, perPage: Int?): PagingSource<Int, WallpaperPhoto>

    suspend fun recent(page: Int?, perPage: Int?): PagingSource<Int, WallpaperPhoto>

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