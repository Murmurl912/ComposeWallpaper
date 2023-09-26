package com.example.wallpaper.impl

import com.example.wallpaper.WallpaperCategory
import com.example.wallpaper.WallpaperPhoto
import com.example.wallpaper.WallpaperProvider
import com.example.wallpaper.WallpaperService

class WallpaperServiceImpl(private val provider: WallpaperProvider): WallpaperService {
    override suspend fun wallpapers(
        category: WallpaperCategory?,
        page: Int?,
        perPage: Int?
    ): Result<List<WallpaperPhoto>> {
        return kotlin.runCatching {
            provider.wallpapers(category, page, perPage)
        }
    }

    override suspend fun search(
        query: String,
        color: Int?,
        orientation: WallpaperService.PhotoOrientation?,
        orderBy: WallpaperService.PhotoOrder?,
        page: Int?,
        perPage: Int?
    ): Result<List<WallpaperPhoto>> {
        return kotlin.runCatching {
            provider.search(query, color, orientation, orderBy, page, perPage)
        }
    }

    override suspend fun categories(page: Int?, perPage: Int?): Result<List<WallpaperCategory>> {
        return kotlin.runCatching {
            provider.categories(page, perPage)
        }
    }

    override suspend fun setWallpaper(
        photo: WallpaperPhoto,
        screen: WallpaperService.WallpaperScreen
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun favorite(photo: WallpaperPhoto) {
        TODO("Not yet implemented")
    }

    override suspend fun unfavorite(photo: WallpaperPhoto) {
        TODO("Not yet implemented")
    }

    override suspend fun favorites(page: Int?, perPage: Int?): List<WallpaperPhoto> {
        TODO("Not yet implemented")
    }

    override suspend fun recent(page: Int?, perPage: Int?): List<WallpaperPhoto> {
        TODO("Not yet implemented")
    }
}