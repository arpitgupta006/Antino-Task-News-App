package com.arpit.newsapp3

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

const val base_url = "https://newsapi.org/"
const val api_key = "87549592e55f41b986f248237a219d90#"


interface APIService {
    @GET("v2/everything?q=tesla&from=2021-04-20&sortBy=publishedAt&apiKey=$api_key")

    fun getHeadlines() : Call<News>


    //https://newsapi.org/v2/top-headlines?apiKey=$api_key&country=in&page=2
}

object  newsService {
    val apiService : APIService
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(APIService::class.java)
    }
}