package com.arpit.antinotasknewsapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.arpit.newsapp3.MyAdapter
import com.arpit.newsapp3.News
import com.arpit.newsapp3.newsService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var newsadapter : MyAdapter
    private var articlesList = mutableListOf<Article>()
    var pagenum = 1
    var totalResultsNews = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        newsadapter = MyAdapter(this@MainActivity, articlesList)
        rvNewsList.adapter = newsadapter
        rvNewsList.layoutManager = LinearLayoutManager(this@MainActivity)


        getNews()
    }

    private fun getNews() {
        Log.d("mainActivity" , "Requestsent for $pagenum")
        val news = newsService.apiService.getHeadlines()
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if (news!= null) {
                    // Log.d("Loading" , news.toString() )
                    totalResultsNews = news.totalResults
                    articlesList.addAll(news.articles)
                    newsadapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("ErrorLoading" , "Can't fetch news"  , t)
            }

        })
    }
}