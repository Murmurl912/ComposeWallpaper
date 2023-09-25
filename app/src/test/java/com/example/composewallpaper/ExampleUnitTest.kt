package com.example.composewallpaper

import com.example.composewallpaper.wallpaper.pexels.pexelOkhttpApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun test() = runBlocking {
        val api = pexelOkhttpApi("")
        val photos = api.featuredPhoto(null, null)
        println(photos)
    }
}