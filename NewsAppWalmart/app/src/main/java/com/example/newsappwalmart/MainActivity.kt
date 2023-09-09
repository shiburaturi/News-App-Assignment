package com.example.newsappwalmart

import android.os.Bundle
import androidx.activity.ComponentActivity
import android.net.Uri
import android.util.Log
import android.widget.ListView
import android.widget.SearchView
import androidx.browser.customtabs.CustomTabsIntent
import com.example.newsappwalmart.newsmodel.NewsData
import com.example.newsappwalmart.newsviewmodel.NewsAdapter
import com.example.newsappwalmart.newsmodel.NewsAPIService

class MainActivity : ComponentActivity() {

    private lateinit var mAdapter: NewsAdapter
    private lateinit var listView: ListView
    private lateinit var searchView: SearchView
    private lateinit var originalNewsList: ArrayList<NewsData>
    private lateinit var newsApiService: NewsAPIService

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsApiService = NewsAPIService(this)
        originalNewsList = ArrayList()

        listView = findViewById(R.id.listView)
        searchView = findViewById(R.id.searchView)

        mAdapter = NewsAdapter { item ->
            onItemClicked(item)
        }
        listView.adapter = mAdapter
        fetchData()

        listView.setOnItemClickListener { _, _, position, _ ->
            onItemClicked(mAdapter.getItem(position))
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                performSearch(query)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                mAdapter.updateNews(originalNewsList)
                return true
            }
        })
    }

    private fun fetchData() {
        newsApiService.fetchData(
            onSuccess = { newsArray ->
                originalNewsList.clear()
                originalNewsList.addAll(newsArray)
                mAdapter.updateNews(newsArray)
            },
            onError = {
                Log.i(TAG, "API Error")
            }
        )
    }

    private fun onItemClicked(item: NewsData) {
        val builder =  CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
    }

    private fun performSearch(query: String?) {
        if (query.isNullOrBlank()) {
            mAdapter.updateNews(originalNewsList)
        } else {
            val filteredList = originalNewsList.filter { news ->
                news.title.contains(query, ignoreCase = true) ||
                        news.description.contains(query, ignoreCase = true) ||
                        news.author.contains(query, ignoreCase = true)
            }
            mAdapter.updateNews(filteredList as ArrayList<NewsData>)
        }
    }
}