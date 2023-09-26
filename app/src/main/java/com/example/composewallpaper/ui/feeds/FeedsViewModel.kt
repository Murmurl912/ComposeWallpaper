package com.example.composewallpaper.ui.feeds

import androidx.compose.runtime.MutableState
import androidx.compose.ui.tooling.preview.Wallpaper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpaper.WallpaperCategory
import com.example.wallpaper.WallpaperService
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.launch

class FeedsViewModel(): ViewModel() {

    private val service = WallpaperService.create()


    private val state: MutableStateFlow<WallpaperFeedState> = MutableStateFlow(WallpaperFeedState.Loading)

    val feedsState = state.asStateFlow()

    init {
        refresh()
    }
    fun refresh() {
        viewModelScope.launch {
            val categories = service.categories(1, 20)
                .onFailure {
                    state.value = WallpaperFeedState.Failure(it.message?:"Unable to load categories")
                }.getOrNull()
            if (categories == null) {
                return@launch
            }
            if ( categories.isEmpty()) {
                state.value = WallpaperFeedState.Failure("Unable to load categories")
                return@launch
            }

            val category = categories.first()

            service.wallpapers(category)
                .onFailure {
                    state.value = WallpaperFeedState.Failure("Unable to load wallpapers")
                }
                .onSuccess {
                    state.value = WallpaperFeedState.Loaded(
                        WallpaperCategoryListState(
                            false,
                            category,
                            categories
                        ),
                        WallpaperPhotoListState(
                            false,
                            it
                        )
                    )
                }


        }
    }

    fun select(category: WallpaperCategory) {


    }
}