package com.example.unsplash

import okhttp3.Interceptor
import okhttp3.Response

class UnsplashAuthInterceptor(private val provider: UnsplashApiAuthProvider):  Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = provider.authorize(chain.request().newBuilder())
            .build()
        return chain.proceed(request)
    }

}