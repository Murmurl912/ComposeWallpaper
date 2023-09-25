package com.example.composewallpaper.wallpaper

interface Photo {

    fun id(): String

    fun ratio(): Float

    fun color(): Int

    fun width(): Int

    fun height(): Int

    fun urls(): PhotoUrls

    fun author(): Author
}


interface PhotoUrls {

    fun original(): String

    fun large(): String

    fun medium(): String

    fun small(): String

    fun thumbnail(): String
}

interface Author {

    fun name(): String

    fun avatar(): String

    fun url(): String
}