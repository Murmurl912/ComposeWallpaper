package com.example.unsplash.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoCollection(
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
    val cover_photo: Photo?,
    val preview_photos: List<Photo>?,
    val user: User?,
    val links: CollectionLinks?,
) : Parcelable

@Parcelize
data class CollectionLinks(
    val self: String,
    val html: String,
    val photos: String
) : Parcelable