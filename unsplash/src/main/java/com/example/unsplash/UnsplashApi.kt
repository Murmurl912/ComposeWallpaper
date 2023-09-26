package com.example.unsplash

interface UnsplashApi {

    fun topicApi(): TopicApi

    fun searchApi(): SearchApi

    fun collectionApi(): CollectionApi

    fun photoApi(): PhotoApi

}