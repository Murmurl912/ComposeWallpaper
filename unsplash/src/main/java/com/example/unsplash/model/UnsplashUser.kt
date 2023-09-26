package com.example.unsplash.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@JsonClass(generateAdapter = true)
@Parcelize
data class UnsplashUser(
    val id: String,
    val updated_at: String?,
    val username: String?,
    val name: String?,
    val first_name: String?,
    val last_name: String?,
    val instagram_username: String?,
    val twitter_username: String?,
    val portfolio_url: String?,
    val bio: String?,
    val location: String?,
    val total_likes: Int?,
    val total_photos: Int?,
    val total_collections: Int?,
    val followed_by_user: Boolean?,
    val followers_count: Int?,
    val following_count: Int?,
    val downloads: Int?,
    val profile_image: UnsplashProfileImage?,
    val badge: UnsplashBadge?,
    val links: UnsplashUserLinks?,
    val photos: List<UnsplashPhoto>?,
) : Parcelable


@JsonClass(generateAdapter = true)
@Parcelize
data class UnsplashProfileImage(
    val small: String,
    val medium: String,
    val large: String,
) : Parcelable


@JsonClass(generateAdapter = true)
@Parcelize
data class UnsplashBadge(
    val title: String?,
    val primary: Boolean?,
    val slug: String?,
    val link: String?,
) : Parcelable


@JsonClass(generateAdapter = true)
@Parcelize
data class UnsplashUserLinks(
    val self: String,
    val html: String,
    val photos: String,
    val likes: String,
    val portfolio: String,
    val following: String,
    val followers: String,
) : Parcelable