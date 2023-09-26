package com.example.pexels.services

import com.example.pexels.api.PexelApi
import com.example.pexels.api.PexelCollection
import com.example.pexels.api.PexelPhoto
import com.example.pexels.moshi.pexelMoshiAdapter
import com.example.pexels.services.impl.PexelServiceImpl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Locale

interface PexelService {

    suspend fun searchPhotos(
        query: String,
        orientation: Orientation? = null,
        size: PhotoSize? = null,
        color: Int? = null,
        locale: Locale? = null,
        page: Int? = null,
        perPage: Int? = null
    ): Result<List<PexelPhoto>>

    suspend fun featurePhotos(page: Int? = null, perPage: Int?): Result<List<PexelPhoto>>

    suspend fun collections(page: Int?, perPage: Int?): Result<List<PexelCollection>>

    suspend fun collectionPhotos(collection: PexelCollection, page: Int? = null, perPage: Int?): Result<List<PexelPhoto>>


    enum class Orientation {
        Landscape,
        Protrait,
        Square
    }

    @JvmInline
    value class PhotoSize(val sizeInMb: Int)


    companion object {

        fun createPexelApi(apiEndpoint: String = "https://api.pexels.com/", apiKey: () -> String): PexelApi {
            val moshi = pexelMoshiAdapter
            val client = OkHttpClient.Builder()
                .addInterceptor {
                    val request = it.request().newBuilder()
                        .addHeader("Authorization", apiKey())
                        .build()
                    it.proceed(request)
                }
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.pexels.com/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(client)
                .build()
            return retrofit.create(PexelApi::class.java)
        }

        fun create(apiKey: () -> String): PexelService {
            val api = createPexelApi(apiKey = apiKey)
            return PexelServiceImpl(api)
        }
    }
}

