package com.example.beupdated

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsInterface {
    @GET("v2/top-headlines?country=in&apiKey=21ea703399a94cc990aa9b26e1e9bb0d")
    fun getDataNews(@Query("category") category: String):Call<NewsDataModel>
}