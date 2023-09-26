package com.example.pexels.api

import com.example.pexels.moshi.pexelMoshiAdapter
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PexelApi {


    /**
     * 此端点使你可以在Pexels中搜索任意主题。例如，你的查询范围可以很宽泛，如Nature、Tigers、People；也可以很具体，如Group of people working。
     * @param query The search query. Ocean, Tigers, Pears, etc.
     * @param orientation Desired photo orientation. The current supported orientations are: landscape, portrait or square
     * @param size Minimum photo size. The current supported sizes are: large(24MP), medium(12MP) or small(4MP).
     * @param color Desired photo color. Supported colors: red, orange, yellow, green, turquoise, blue, violet, pink, brown, black, gray, white or any hexidecimal color code (eg. #ffffff).
     * @param locale The locale of the search you are performing. The current supported locales are: 'en-US' 'pt-BR' 'es-ES' 'ca-ES' 'de-DE' 'it-IT' 'fr-FR' 'sv-SE' 'id-ID' 'pl-PL' 'ja-JP' 'zh-TW' 'zh-CN' 'ko-KR' 'th-TH' 'nl-NL' 'hu-HU' 'vi-VN' 'cs-CZ' 'da-DK' 'fi-FI' 'uk-UA' 'el-GR' 'ro-RO' 'nb-NO' 'sk-SK' 'tr-TR' 'ru-RU'.
     * @param page The page number you are requesting. Default: 1
     * @param perPage The number of results you are requesting per page. Default: 15 Max: 80
     */
    @GET("v1/search")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("orientation") orientation: String?,
        @Query("size") size: String?,
        @Query("color") color: String?,
        @Query("locale") locale: String?,
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?,
    ): PexelPhotoPageData

    /**
     * 此端点使你可以接收Pexels团队精心挑选的实时图片。
     * 我们每小时至少添加一张新图片到精选列表中，因此你始终可以获得不断更新的人气图片精选集。
     * @param page The page number you are requesting. Default: 1
     * @param perPage The number of results you are requesting per page. Default: 15 Max: 80
     */
    @GET("v1/curated")
    suspend fun featuredPhoto(
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?
    ): PexelPhotoPageData

    /**
     * 通过其ID检索特定的Photo。
     * @param id The id of the photo you are requesting.
     */
    @GET("v1/photos/{id}")
    suspend fun photo(@Path("id") id: Int): PexelPhoto

    /*
     * @param page The page number you are requesting. Default: 1
     * @param perPage The number of results you are requesting per page. Default: 15 Max: 80
    */
    @GET("v1/collections/featured")
    suspend fun collections(
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?
    ): PexelCollectionPageData

    /**
     * GET https://api.pexels.com/v1/collections/:id
     * 此端点返回一个收藏夹中的所有媒体（图片和视频）。使用type参数可以过滤只接收图片或视频。
     * @param type The type of media you are requesting. If not given or if given with an invalid value, all media will be returned. Supported values are photos and videos.
     * @param integer | optional The page number you are requesting. Default: 1
     * @param The number of results you are requesting per page. Default: 15 Max: 80
     */
    @GET("v1/collections/{id}")
    suspend fun collectionMedias(
        @Path("id") id: String,
        @Query("type") type: String?,
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?
    ): PexelMediaPageData

    @GET("videos/search")
    suspend fun searchVideos(
        @Query("query") query: String,
        @Query("orientation") orientation: String?,
        @Query("size") size: String?,
        @Query("locale") locale: String?,
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?
    ): PexelVideoPageData

    @GET("videos/popular")
    suspend fun featuredVideos(
        @Query("min_width") minWidth: Int?,
        @Query("min_height") minHeight: Int?,
        @Query("min_duration") minDuration: Int?,
        @Query("max_duration") maxDuration: Int?,
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?
    ): PexelVideoPageData

    @GET("videos/{id}")
    suspend fun video(@Path("id") id: Int): PexelVideo
}