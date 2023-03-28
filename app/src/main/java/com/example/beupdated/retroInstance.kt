package com.example.beupdated

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retroInstance {
    val retro = Retrofit.Builder().baseUrl("https://newsapi.org/")
        .addConverterFactory(GsonConverterFactory.create()).build()
    var NewsInterface = retro.create(NewsInterface::class.java)
}