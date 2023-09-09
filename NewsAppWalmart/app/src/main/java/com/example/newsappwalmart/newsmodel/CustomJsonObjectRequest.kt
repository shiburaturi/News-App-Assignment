package com.example.newsappwalmart.newsmodel

import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject

class CustomJsonObjectRequest(
    method: Int,
    url: String,
    requestBody: JSONObject?,
    listener: Response.Listener<JSONObject>,
    errorListener: Response.ErrorListener
) : JsonObjectRequest(method, url, requestBody, listener, errorListener) {
    override fun getHeaders(): Map<String, String> {
        val headers = HashMap<String, String>()
        headers["User-Agent"] = "Mozilla/5.0"
        return headers
    }
}

