package com.example.wallpaper.impl

import androidx.paging.PagingSource
import com.example.wallpaper.WallpaperCategory
import com.example.wallpaper.WallpaperCategoryPagingSource
import com.example.wallpaper.WallpaperPhoto
import com.example.wallpaper.WallpaperProvider
import com.example.wallpaper.WallpaperSearchPagingSource
import com.example.wallpaper.WallpaperService
import com.example.wallpaper.WallpapersPagingSource

class WallpaperServiceImpl(private val provider: WallpaperProvider): WallpaperService {

    override fun wallpapers(category: WallpaperCategory?): PagingSource<Int, WallpaperPhoto> {
        return WallpapersPagingSource(provider, category)
    }

    override fun search(
        query: String,
        color: Int?,
        orientation: WallpaperService.PhotoOrientation?,
        orderBy: WallpaperService.PhotoOrder?,
    ): PagingSource<Int, WallpaperPhoto> {
        return WallpaperSearchPagingSource(provider, query, color, orientation, orderBy)
    }

    override fun categories(): PagingSource<Int, WallpaperCategory> {
        return WallpaperCategoryPagingSource(provider)
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

    override suspend fun favorites(page: Int?, perPage: Int?): PagingSource<Int, WallpaperPhoto> {
        TODO("Not yet implemented")
    }

    override suspend fun recent(page: Int?, perPage: Int?): PagingSource<Int, WallpaperPhoto> {
        TODO("Not yet implemented")
    }
}