package com.example.newsapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsInterface {
    @GET(value= "v2/top-headlines?apiKey=$API_KEY")
    fun getHeadlines(@Query("country") country: String, @Query("page") page:Int): Call<News>
}