package com.example.unsplash.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UnsplashPhoto(
    val id: String,
    val created_at: String?,
    val updated_at: String?,
    val width: Int,
    val height: Int,
    val color: String? = "#E0E0E0",
    val blur_hash: String?,
    val views: Int?,
    val downloads: Int?,
    val likes: Int?,
    var liked_by_user: Boolean?,
    val description: String?,
    val alt_description: String?,
    val exif: Exif?,
    val location: Location?,
    val tags: List<PhotoTag>?,
    val current_user_collection: List<UnsplashPhotoCollection>?,
    val sponsorship: Sponsorship?,
    val urls: PhotoUrls,
    val links: PhotoLinks?,
    val user: UnsplashUser?,
    val statistics: PhotoStatistics?,
) : Parcelable

@Parcelize
data class Exif(
    val make: String?,
    val model: String?,
    val exposure_time: String?,
    val aperture: String?,
    val focal_length: String?,
    val iso: Int?,
) : Parcelable

@Parcelize
data class Location(
    val title: String?,
    val name: String?,
    val city: String?,
    val country: String?,
    val position: Position?,
) : Parcelable {

}
@Parcelize
data class Position(
    val latitude: Double?,
    val longitude: Double?,
) : Parcelable

@Parcelize
data class PhotoTag(
    val type: String?,
    val title: String?,
) : Parcelable

@Parcelize
data class PhotoUrls(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String
) : Parcelable

@Parcelize
data class PhotoLinks(
    val self: String,
    val html: String,
    val download: String,
    val download_location: String,
) : Parcelable

@Parcelize
data class Sponsorship(
    val sponsor: UnsplashUser?
) : Parcelable

@Parcelize
data class PhotoStatistics(
    val id: String,
    val downloads: PhotoDownloads,
    val views: PhotoViews,
    val likes: PhotoLikes,
) : Parcelable

@Parcelize
data class PhotoDownloads(
    val total: Int,
    val historical: Historical,
) : Parcelable

@Parcelize
data class PhotoViews(
    val total: Int,
    val historical: Historical,
) : Parcelable

@Parcelize
data class PhotoLikes(
    val total: Int,
    val historical: Historical,
) : Parcelable

@Parcelize
data class Historical(
    val change: Int,
    val resolution: String,
    val quality: String,
    val values: List<Value>,
) : Parcelable

@Parcelize
data class Value(
    val date: String,
    val value: Int
) : Parcelable