package com.example.unsplash

import com.example.unsplash.model.UnsplashPhoto
import com.example.unsplash.model.UnsplashPhotoCollection
import com.example.unsplash.model.UnsplashSearchResult
import com.example.unsplash.model.UnsplashUser
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
    ): Call<UnsplashSearchResult<UnsplashPhoto>>

    @GET("/search/collections")
    fun searchCollections(
        @Query("query") query: String,
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?
    ): Call<UnsplashSearchResult<UnsplashPhotoCollection>>

    @GET("/search/users")
    fun searchUsers(
        @Query("query") query: String,
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?
    ): Call<UnsplashSearchResult<UnsplashUser>>

}