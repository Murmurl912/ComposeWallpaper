package com.example.unsplash

import com.example.unsplash.model.PhotoStatistics
import com.example.unsplash.model.UnsplashPhoto
import com.example.unsplash.model.UnsplashPhotoCollection
import com.example.unsplash.model.UnsplashSearchResult
import com.example.unsplash.model.UnsplashTopic
import com.example.unsplash.model.UnsplashUser
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashApi {


    @GET("/search/photos")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?,
        @Query("order_by") orderBy: String?,
        @Query("collections") collections: String?,
        @Query("content_filter") contentFilter: String?,
        @Query("color") color: String?,
        @Query("orientation") orientation: String?,
        @Query("lang") lang: String?
    ): UnsplashSearchResult<UnsplashPhoto>

    @GET("/search/collections")
    suspend fun searchCollections(
        @Query("query") query: String,
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?
    ): UnsplashSearchResult<UnsplashPhotoCollection>

    @GET("/search/users")
    suspend fun searchUsers(
        @Query("query") query: String,
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?
    ): UnsplashSearchResult<UnsplashUser>

    @GET("/collections")
    suspend fun collections(
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?
    ): List<UnsplashPhotoCollection>

    @Headers("Accept-Version: v1")
    @GET("/collections/{id}")
    suspend fun collection(
        @Path("id") id: String
    ): UnsplashPhotoCollection


    @Headers("Accept-Version: v1")
    @GET("/collections/{id}/photos")
    suspend fun collectionPhotos(
        @Path("id") id: String,
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?,
        @Query("orientation") orientation: String?): UnsplashPhoto

    @Headers("Accept-Version: v1")
    @GET("/collections/{id}/related")
    suspend fun relatedCollections(@Path("id") id: String): List<UnsplashPhotoCollection>

    /**
     * 从编辑Feeds中获取一页图片。
     * @param page 页码 可选 默认1
     * @param perPage 每页图片数 可选, 默认10
     * @param orderBy 排序方式 可选 从latest, oldest, popular中选择，默认为latest
     */
    @Headers("Accept-Version: v1")
    @GET("/photos")
    suspend fun photos(@Query("page") page: Int? = null,
               @Query("per_page") perPage: Int? = null,
               @Query("order_by") orderBy: String? = null): List<UnsplashPhoto>

    /**
     * 获取单张图片
     * @param id 图片id
     */
    @Headers("Accept-Version: v1")
    @GET("/photos/{id}")
    suspend fun photo(@Path("id") id: String): UnsplashPhoto

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
    suspend fun random(@Query("collections") collections: String? = null,
               @Query("topics") topics: String? = null,
               @Query("username") username: String? = null,
               @Query("query") query: String? = null,
               @Query("orientation") orientation: String? = null,
               @Query("content_filter") content_filter: String? = null): UnsplashPhoto

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
    suspend fun random(@Query("collections") collections: String? = null,
               @Query("topics") topics: String? = null,
               @Query("username") username: String? = null,
               @Query("query") query: String? = null,
               @Query("orientation") orientation: String? = null,
               @Query("content_filter") contentFilter: String? = null,
               @Query("count") count: Int): List<UnsplashPhoto>

    /**
     * 获取图片的统计信息，如下载，查看，点赞等
     */
    @Headers("Accept-Version: v1")
    @GET("/photos/{id}/statistics")
    suspend fun statistics(@Path("id") id: String): PhotoStatistics

    @Headers("Accept-Version: v1")
    @GET("/photos/{id}/download")
    suspend fun trackDownload(@Path("id") id: String): Map<String, String>

    /**
     * 获取Topic列表
     */
    @GET("/topics")
    suspend fun getTopics(
        @Query("ids")
        ids: String? = null,
        @Query("page")
        page: Int? = null,
        @Query("per_page")
        perPage: Int? = null,
        @Query("order_by")
        orderBy: String? = null): List<UnsplashTopic>

    /**
     * 获取Topic详情
     * @param id topic的id或者slug
     */
    @GET("/topics/:id")
    suspend fun getTopic(@Path("id") id: String): UnsplashTopic

    /**
     * 获取Topic下的图片
     * @param id topic的id或者slug
     */
    @GET("/topics/{id}/photos")
    suspend fun getPhotos(@Path("id") id: String,
                  @Query("page") page: Int? = null,
                  @Query("per_page") perPage: Int? = null,
                  @Query("orientation") orientation: String? = null,
                  @Query("order_by") orderBy: String? = null): List<UnsplashPhoto>
}