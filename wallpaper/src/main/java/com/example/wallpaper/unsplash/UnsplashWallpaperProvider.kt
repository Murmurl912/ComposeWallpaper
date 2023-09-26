package com.example.wallpaper.unsplash

import android.graphics.Color
import com.example.unsplash.UnsplashApi
import com.example.unsplash.model.UnsplashPhoto
import com.example.unsplash.model.UnsplashTopic
import com.example.unsplash.model.UnsplashUser
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
        val photos = if (category != null) {
            unsplashApi.topicPhotos(
                category.id(),
                page,
                perPage
            )
        } else {
            unsplashApi.photos(page, perPage)
        }
        return photos.map {
            UnsplashWallpaperPhoto(it)
        }
    }

    override suspend fun categories(page: Int?, perPage: Int?): List<WallpaperCategory> {
        return unsplashApi.topics(page = page, perPage = perPage)
            .map {
                UnsplashWallpaperCategory(
                    it
                )
            }

    }

    override suspend fun search(
        query: String,
        color: Int?,
        orientation: WallpaperService.PhotoOrientation?,
        orderBy: WallpaperService.PhotoOrder?,
        page: Int?,
        perPage: Int?
    ): List<WallpaperPhoto> {
        return unsplashApi.searchPhotos(query, page, perPage, orientation = orientation?.name)
            .results
            .map {
                UnsplashWallpaperPhoto(it)
            }
    }
}

data class UnsplashWallpaperCategory(
    val topic: UnsplashTopic
): WallpaperCategory {

    override fun id(): String {
        return topic.id
    }

    override fun title(): String {
        return topic.title
    }

    override fun description(): String? {
        return topic.description
    }

    override fun covers(): List<WallpaperPhoto> {
        return topic.cover_photo?.let {
            listOf(UnsplashWallpaperPhoto(it))
        } ?: emptyList()
    }

    override fun totalPhotos(): Int {
        return topic.total_photos?: 0
    }
}

data class UnsplashWallpaperPhoto(
    val photo: UnsplashPhoto
): WallpaperPhoto {
    override fun id(): String {
        return photo.id
    }

    override fun width(): Int {
        return photo.width
    }

    override fun height(): Int {
        return photo.height
    }

    override fun ratio(): Float {
        return photo.width.toFloat() / photo.height.toFloat()
    }

    override fun blur(): String? {
        return photo.blur_hash
    }

    override fun color(): Int {
        return photo.color.toHexColor() ?: 0
    }

    override fun wallpaper(): String {
        return photo.urls.full
    }

    override fun preview(): String {
        return photo.urls.regular
    }

    override fun thumbnail(): String {
        return photo.urls.small
    }

    override fun author(): WallpaperAuthor? {
        return photo.user?.let {
            UnsplashWallpaperAuthor(it)
        }
    }

    override fun description(): String? {
        return photo.description
    }

    override fun favorite(): Boolean {
        return false
    }


    private fun String?.toHexColor(): Int? =
        if (this == null) {
            null
        } else {
            kotlin.runCatching {
                Color.parseColor(this)
            }.getOrNull()
        }
}

data class UnsplashWallpaperAuthor(
    private val user: UnsplashUser
): WallpaperAuthor {
    override fun name(): String? {
        return user.name
    }

    override fun link(): String? {
        return user.links?.self
    }

    override fun avatar(): String? {
        return user.profile_image?.large
    }
}