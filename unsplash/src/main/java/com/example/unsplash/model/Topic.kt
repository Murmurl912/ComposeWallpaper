package com.example.unsplash.model

data class Topic(
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
    val links: TopicLinks?,
    val status: String,
    val owners: List<User>,
    val cover_photo: Photo?,
    val preview_photos: List<Photo>?
)

data class TopicLinks(
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