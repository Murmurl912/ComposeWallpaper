package com.example.composewallpaper

import com.example.composewallpaper.wallpaper.pexels.pexelOkhttpApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PexelApiTest {

    private val api = pexelOkhttpApi("")
    @Test
    fun testFeaturedPhoto(): Unit = runBlocking {
        api.featuredPhoto(null, null)
        api.featuredPhoto(2, null)
    }
}