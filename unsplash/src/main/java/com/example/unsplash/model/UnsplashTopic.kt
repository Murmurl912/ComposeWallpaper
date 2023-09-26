package com.example.unsplash.model

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class UnsplashTopic(
    val id: String,
    val slug: String,
    val title: String,
    val description: String?,
    val published_at: String?,
    val updated_at: String?,
    val started_at: String?,
    val ends_at: String?,
    val only_submissions_after: String?,
    val visibility: String?,
    val featured: Boolean?,
    val total_photos: Int?,
    val links: UnsplashTopicLinks?,
    val status: String,
    val owners: List<UnsplashUser>,
    val cover_photo: UnsplashPhoto?,
)

@JsonClass(generateAdapter = true)
data class UnsplashTopicLinks(
    val self: String?,
    val html: String?,
    val photos: String?
) {
    override fun toString(): String {
        return "TopicLinks(" +
                "self=$self, " +
                "html=$html, " +
                "photos=$photos" +
                ")"
    }
}