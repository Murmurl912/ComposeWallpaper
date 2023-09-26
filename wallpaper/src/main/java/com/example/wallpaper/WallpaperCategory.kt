package com.example.wallpaper

interface WallpaperCategory {

    fun id(): String

    fun title(): String

    fun description(): String?

    fun covers(): List<WallpaperPhoto>

    fun totalPhotos(): Int

}