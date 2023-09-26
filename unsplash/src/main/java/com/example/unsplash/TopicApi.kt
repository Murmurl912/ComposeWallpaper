package com.example.unsplash

import com.example.unsplash.model.Photo
import com.example.unsplash.model.Topic
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TopicApi {

    /**
     * 获取Topic列表
     */
    @GET("/topics")
    fun getTopics(
        @Query("ids")
        ids: String? = null,
        @Query("page")
        page: Int? = null,
        @Query("per_page")
        perPage: Int? = null,
        @Query("order_by")
        orderBy: String? = null): Call<List<Topic>>

    /**
     * 获取Topic详情
     * @param id topic的id或者slug
     */
    @GET("/topics/:id")
    fun getTopic(@Path("id") id: String): Call<Topic>

    /**
     * 获取Topic下的图片
     * @param id topic的id或者slug
     */
    @GET("/topics/{id}/photos")
    fun getPhotos(@Path("id") id: String,
                  @Query("page") page: Int? = null,
                  @Query("per_page") perPage: Int? = null,
                  @Query("orientation") orientation: String? = null,
                  @Query("order_by") orderBy: String? = null): Call<List<Photo>>

}