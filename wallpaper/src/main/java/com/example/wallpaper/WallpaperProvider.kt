package com.example.wallpaper

import androidx.paging.PagingSource
import androidx.paging.PagingState

interface WallpaperProvider {

    suspend fun wallpapers(
        category: WallpaperCategory? = null,
        page: Int? = null,
        perPage: Int? = null): List<WallpaperPhoto>

    suspend fun categories(page: Int? = null,
                           perPage: Int? = null): List<WallpaperCategory>

    suspend fun search(
        query: String,
        color: Int? = null,
        orientation: WallpaperService.PhotoOrientation? = null,
        orderBy: WallpaperService.PhotoOrder? = null,
        page: Int? = null,
        perPage: Int? = null
    ): List<WallpaperPhoto>
}

class WallpapersPagingSource(
    private val wallpaperProvider: WallpaperProvider,
    private val category: WallpaperCategory?,
): PagingSource<Int, WallpaperPhoto>() {

    override fun getRefreshKey(state: PagingState<Int, WallpaperPhoto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WallpaperPhoto> {
        val page = params.key?:1
        val size = params.loadSize

        val result = kotlin.runCatching {
            wallpaperProvider.wallpapers(category, page, size)
        }

        return if (result.isSuccess) {
            val wallpapers = result.getOrThrow()
            LoadResult.Page(
                wallpapers,
                if (page <= 1) {
                    null
                } else {
                    page - 1
                },
                if (wallpapers.isEmpty()) {
                    null
                } else {
                    page + 1
                }
            )
        } else {
            LoadResult.Error(
                result.exceptionOrNull()?:Exception()
            )
        }
    }

}

class WallpaperCategoryPagingSource(
    private val wallpaperProvider: WallpaperProvider,
): PagingSource<Int, WallpaperCategory>() {

    override fun getRefreshKey(state: PagingState<Int, WallpaperCategory>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WallpaperCategory> {
        val page = params.key?:1
        val size = params.loadSize

        val result = kotlin.runCatching {
            wallpaperProvider.categories(page, size)
        }

        return if (result.isSuccess) {
            val categories = result.getOrThrow()
            LoadResult.Page(
                categories,
                if (page <= 1) {
                    null
                } else {
                    page - 1
                },
                if (categories.isEmpty()) {
                    null
                } else {
                    page + 1
                }
            )
        } else {
            LoadResult.Error(
                result.exceptionOrNull()?:Exception()
            )
        }
    }
}

class WallpaperSearchPagingSource(
    private val wallpaperProvider: WallpaperProvider,
    private val query: String,
    private val color: Int? = null,
    private val orientation: WallpaperService.PhotoOrientation? = null,
    private val orderBy: WallpaperService.PhotoOrder? = null
): PagingSource<Int, WallpaperPhoto>() {

    override fun getRefreshKey(state: PagingState<Int, WallpaperPhoto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WallpaperPhoto> {
        val page = params.key?:1
        val size = params.loadSize

        val result = kotlin.runCatching {
            wallpaperProvider.search(
                query,
                page = page,
                perPage = size,
                color = color,
                orderBy = orderBy,
                orientation = orientation
            )
        }

        return if (result.isSuccess) {
            val wallpapers = result.getOrThrow()
            LoadResult.Page(
                wallpapers,
                if (page <= 1) {
                    null
                } else {
                    page - 1
                },
                if (wallpapers.isEmpty()) {
                    null
                } else {
                    page + 1
                }
            )
        } else {
            LoadResult.Error(
                result.exceptionOrNull()?:Exception()
            )
        }
    }
}