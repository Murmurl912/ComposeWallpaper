package com.example.wallpaper

interface WallpaperPhoto {

    fun id(): String

    fun width(): Int

    fun height(): Int

    fun ratio(): Float

    fun blur(): String?

    fun color(): Int

    fun wallpaper(): String

    fun preview(): String

    fun thumbnail(): String

    fun author(): WallpaperAuthor?

    fun description(): String?

    fun favorite(): Boolean

}