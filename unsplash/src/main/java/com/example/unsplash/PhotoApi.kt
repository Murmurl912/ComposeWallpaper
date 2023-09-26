package com.example.unsplash

import com.example.unsplash.model.Photo
import com.example.unsplash.model.PhotoStatistics
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface PhotoApi {

    /**
     * 从编辑Feeds中获取一页图片。
     * @param page 页码 可选 默认1
     * @param perPage 每页图片数 可选, 默认10
     * @param orderBy 排序方式 可选 从latest, oldest, popular中选择，默认为latest
     */
    @Headers("Accept-Version: v1")
    @GET("/photos")
    fun photos(@Query("page") page: Int? = null,
               @Query("per_page") perPage: Int? = null,
               @Query("order_by") orderBy: String? = null): Call<List<Photo>>

    /**
     * 获取单张图片
     * @param id 图片id
     */
    @Headers("Accept-Version: v1")
    @GET("/photos/{id}")
    fun photo(@Path("id") id: String): Call<Photo>

    /**
     * 获取一张随机图片。collections, topics 不能同时与query使用
     * @param collections 公共图片集合ID，通过,分隔多个ID
     * @param topics 公共Topic ID，通过,分隔多个ID
     * @param username 限制图片范围到用户
     * @param query 限制图片范围到匹配搜索词的图片
     * @param orientation 图片方向，从landscape, portrait, squarish选择
     * @param content_filter 内容安全过滤，从low, high中选择
     */
    @Headers("Accept-Version: v1")
    @GET("/photos/random")
    fun random(@Query("collections") collections: String? = null,
               @Query("topics") topics: String? = null,
               @Query("username") username: String? = null,
               @Query("query") query: String? = null,
               @Query("orientation") orientation: String? = null,
               @Query("content_filter") content_filter: String? = null): Call<Photo>

    /**
     * 获取一组随机图片。collections, topics 不能同时与query使用
     * @param collections 公共图片集合ID，通过,分隔多个ID
     * @param topics 公共Topic ID，通过,分隔多个ID
     * @param username 限制图片范围到用户
     * @param query 限制图片范围到匹配搜索词的图片
     * @param orientation 图片方向，从landscape, portrait, squarish选择
     * @param content_filter 内容安全过滤，从low, high中选择
     * @param count 返回图片数量
     */
    @Headers("Accept-Version: v1")
    @GET("/photos/random")
    fun random(@Query("collections") collections: String? = null,
               @Query("topics") topics: String? = null,
               @Query("username") username: String? = null,
               @Query("query") query: String? = null,
               @Query("orientation") orientation: String? = null,
               @Query("content_filter") contentFilter: String? = null,
               @Query("count") count: Int): Call<List<Photo>>

    /**
     * 获取图片的统计信息，如下载，查看，点赞等
     */
    @Headers("Accept-Version: v1")
    @GET("/photos/{id}/statistics")
    fun statistics(@Path("id") id: String): Call<PhotoStatistics>

    @Headers("Accept-Version: v1")
    @GET("/photos/{id}/download")
    fun trackDownload(@Path("id") id: String): Call<Map<String, String>>

}