package com.example.composewallpaper.wallpaper.pexels.moshi

import com.example.composewallpaper.wallpaper.pexels.PexelMedia
import com.example.composewallpaper.wallpaper.pexels.PexelPhoto
import com.example.composewallpaper.wallpaper.pexels.PexelVideo
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory

val pexelMoshiAdapter =
    Moshi.Builder()
        .add(
            PolymorphicJsonAdapterFactory.of(
                PexelMedia::class.java, "pexel-media"
            ).withSubtype(PexelPhoto::class.java, "pexel-photo")
                .withSubtype(PexelVideo::class.java, "pexel-vidoe")
        )
        .build()