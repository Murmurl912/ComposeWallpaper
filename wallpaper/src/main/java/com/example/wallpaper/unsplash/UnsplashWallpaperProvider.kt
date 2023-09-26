package com.example.wallpaper.unsplash

import com.example.unsplash.UnsplashApi
import com.example.wallpaper.WallpaperAuthor
import com.example.wallpaper.WallpaperCategory
import com.example.wallpaper.WallpaperPhoto
import com.example.wallpaper.WallpaperProvider
import com.example.wallpaper.WallpaperService

class UnsplashWallpaperProvider(
    private val unsplashApi: UnsplashApi
): WallpaperProvider {

    override suspend fun wallpapers(
        category: WallpaperCategory?,
        page: Int?,
        perPage: Int?
    ): List<WallpaperPhoto> {
        TODO("Not yet implemented")
    }

    override suspend fun categories(page: Int?, perPage: Int?): List<WallpaperCategory> {
        TODO("Not yet implemented")
    }

    override suspend fun search(
        query: String,
        color: Int?,
        orientation: WallpaperService.PhotoOrientation?,
        orderBy: WallpaperService.PhotoOrder?,
        page: Int?,
        perPage: Int?
    ): List<WallpaperPhoto> {
        TODO("Not yet implemented")
    }
}