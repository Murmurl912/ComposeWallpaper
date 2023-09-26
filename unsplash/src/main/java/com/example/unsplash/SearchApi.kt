package com.example.unsplash

import com.example.unsplash.model.Photo
import com.example.unsplash.model.PhotoCollection
import com.example.unsplash.model.SearchResult
import com.example.unsplash.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("/search/photos")
    fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?,
        @Query("order_by") orderBy: String?,
        @Query("collections") collections: String?,
        @Query("content_filter") contentFilter: String?,
        @Query("color") color: String?,
        @Query("orientation") orientation: String?,
        @Query("lang") lang: String?
    ): Call<SearchResult<Photo>>

    @GET("/search/collections")
    fun searchCollections(
        @Query("query") query: String,
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?
    ): Call<SearchResult<PhotoCollection>>

    @GET("/search/users")
    fun searchUsers(
        @Query("query") query: String,
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?
    ): Call<SearchResult<User>>

}