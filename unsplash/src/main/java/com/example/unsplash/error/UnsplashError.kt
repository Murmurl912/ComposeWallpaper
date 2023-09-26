package com.example.unsplash.error

class UnsplashError: Exception {

    constructor(): super()

    constructor(message: String): super(message)
}