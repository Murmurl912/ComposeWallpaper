package com.example.composewallpaper.ui.feeds

import com.example.wallpaper.WallpaperCategory
import com.example.wallpaper.WallpaperPhoto

sealed interface WallpaperFeedState {

    data class Loaded(
        val categoryState: WallpaperCategoryListState,
        val wallpaperState: WallpaperPhotoListState
    ): WallpaperFeedState

    data class Failure(
        val message: String
    ): WallpaperFeedState

    object Loading: WallpaperFeedState
}

data class WallpaperPhotoListState(
    val isLoading: Boolean,
    val wallpapers: List<WallpaperPhoto>,
)

data class WallpaperCategoryListState(
    val isLoading: Boolean,
    val category: WallpaperCategory,
    val categories: List<WallpaperCategory>
)