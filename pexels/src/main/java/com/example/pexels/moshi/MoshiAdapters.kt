package com.example.pexels.moshi


import com.example.pexels.api.PexelMedia
import com.example.pexels.api.PexelPhoto
import com.example.pexels.api.PexelVideo
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