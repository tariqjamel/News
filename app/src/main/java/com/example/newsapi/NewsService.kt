package com.example.newsapi


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "c42495449527457aa8184357c1b78eee"

object  NewsService{
    val newsInstance: NewsInterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstance = retrofit.create(NewsInterface::class.java)
    }
}