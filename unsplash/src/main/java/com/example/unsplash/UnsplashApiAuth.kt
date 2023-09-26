package com.example.unsplash

import okhttp3.Request

class UnsplashApiAuthProvider {

    fun authorize(request: Request.Builder): Request.Builder {
        return request.addHeader(
            HEADER_AUTHORIZATION, "Client-ID ${clientID()}"
        )
    }

    private fun clientID(): String = "ece4fa5efbe76bcec26bf982d7b7ef8b27bd80d832dbe8a40bd954448fc4320a"

    companion object {
        const val HEADER_AUTHORIZATION: String = "Authorization"
    }
}