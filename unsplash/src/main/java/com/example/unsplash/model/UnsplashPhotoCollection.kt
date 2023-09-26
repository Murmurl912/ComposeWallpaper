package com.example.unsplash.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UnsplashPhotoCollection(
    val id: Int,
    val title: String,
    val description: String?,
    val published_at: String?,
    val updated_at: String?,
    val curated: Boolean?,
    val featured: Boolean?,
    val total_photos: Int,
    val private: Boolean?,
    val share_key: String?,
    val tags: List<PhotoTag>?,
    val cover_photo: UnsplashPhoto?,
    val preview_photos: List<UnsplashPhoto>?,
    val user: UnsplashUser?,
    val links: UnsplashCollectionLinks?,
) : Parcelable

@Parcelize
data class UnsplashCollectionLinks(
    val self: String,
    val html: String,
    val photos: String
) : Parcelable