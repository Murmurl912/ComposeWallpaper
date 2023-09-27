package com.example.composewallpaper.ui.feeds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.wallpaper.WallpaperCategory
import com.example.wallpaper.WallpaperPhoto
import com.example.wallpaper.WallpaperService
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicReference

class FeedsViewModel: ViewModel() {

    private val service = WallpaperService.create()
    private val wallpapersCache: MutableMap<WallpaperCategory, Flow<PagingData<WallpaperPhoto>>> = ConcurrentHashMap()

    val categories = Pager(
        config = PagingConfig(
            pageSize = 15,
            enablePlaceholders = false,
            initialLoadSize = 30
        ),
        pagingSourceFactory = {
            service.categories()
        }
    ).flow.cachedIn(viewModelScope)

    fun wallpapers(category: WallpaperCategory): Flow<PagingData<WallpaperPhoto>> {
        return wallpapersCache.computeIfAbsent(category) {
            Pager(
                config = PagingConfig(
                    pageSize = 15,
                    enablePlaceholders = false,
                    initialLoadSize = 30
                ),
                pagingSourceFactory = {
                    service.wallpapers(category)
                }
            ).flow.cachedIn(viewModelScope)
        }
    }


    fun select(category: WallpaperCategory) {

    }

}



data class CategoryListState(
    val category: WallpaperCategory? = null,
    val categories: List<WallpaperCategory> = emptyList(),
    val loadingState: LoadMoreState = LoadMoreState()
)

data class WallpaperListState(
    val category: WallpaperCategory? = null,
    val wallpapers: List<WallpaperPhoto> = emptyList(),
    val loadingState: LoadMoreState = LoadMoreState(),
)

data class LoadMoreState(
    val page: Int = 0,
    val perPage: Int = 0,
    val offset: Int = 0,
    val status: LoadingStatus = LoadingStatus.StatusIdle
)

sealed interface LoadingStatus {

    data class StatusFailure(
        val message: String? = "",
    ): LoadingStatus

    object StatusLoading: LoadingStatus

    object StatusIdle: LoadingStatus

}