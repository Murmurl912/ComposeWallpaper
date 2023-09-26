package com.example.unsplash

import com.example.unsplash.model.Photo
import com.example.unsplash.model.PhotoCollection
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface CollectionApi {
    @Headers("Accept-Version: v1")
    @GET("/collections")
    fun collections(
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?
    ): Call<List<PhotoCollection>>

    @Headers("Accept-Version: v1")
    @GET("/collections/{id}")
    fun collection(
        @Path("id") id: String
    ): Call<PhotoCollection>


    @Headers("Accept-Version: v1")
    @GET("/collections/{id}/photos")
    fun collectionPhotos(
        @Path("id") id: String,
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?,
        @Query("orientation") orientation: String?): Call<List<Photo>>

    @Headers("Accept-Version: v1")
    @GET("/collections/{id}/related")
    fun relatedCollection(@Path("id") id: String): List<PhotoCollection>

}