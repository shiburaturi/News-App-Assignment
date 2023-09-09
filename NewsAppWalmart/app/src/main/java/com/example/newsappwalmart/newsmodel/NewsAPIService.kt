package com.example.newsappwalmart.newsmodel

import android.content.Context
import android.util.Log
import com.android.volley.Request
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NewsAPIService(private val context: Context) {
    companion object {
        private val TAG = NewsAPIService::class.java.simpleName
        private const val BASE_URL = "https://newsapi.org/v2/top-headlines?country=us&apiKey=50c1bd99f6464242aab6405aa2ca35a5"
    }

    fun fetchData(
        onSuccess: (ArrayList<NewsData>) -> Unit,
        onError: () -> Unit
    ) {
        val jsonObjectRequest = CustomJsonObjectRequest(
            Request.Method.GET,
            BASE_URL,
            null,
            {
                val newsJsonArray = it.getJSONArray("articles")
                val newsArray = ArrayList<NewsData>()
                for(i in 0 until newsJsonArray.length()) {
                    val newsJsonObject = newsJsonArray.getJSONObject(i)

                    val authorJson = JSONObject(newsJsonObject.getString("source"))
                    val sourceName = authorJson.getString("name")
                    val publishedAt = newsJsonObject.getString("publishedAt")
                    val formattedPublishedAt = formatPublishedAt(publishedAt) // Format publishedAt

                    val news = NewsData(
                        newsJsonObject.getString("title"),
                        sourceName, // Use extracted source name
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage"),
                        newsJsonObject.getString("description"),
                        formattedPublishedAt // Use formatted publishedAt
                    )
                    newsArray.add(news)
                }
                onSuccess(newsArray)
            },
            {
                Log.i(TAG, "API Error")
                onError()
            }
        )
        NewsSingleton.getInstance(context).addToRequestQueue(jsonObjectRequest)
    }

    private fun formatPublishedAt(rawPublishedAt: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM HH:mm", Locale.getDefault())
        try {
            val date = inputFormat.parse(rawPublishedAt)
            return outputFormat.format(date as Date)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return rawPublishedAt
    }
}