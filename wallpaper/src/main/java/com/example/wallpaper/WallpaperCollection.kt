package com.example.wallpaper

interface WallpaperCollection {

    fun id(): String

    fun title(): String

    fun description(): String?

    fun covers(): List<WallpaperPhoto>

    fun photoCounts(): Int

}