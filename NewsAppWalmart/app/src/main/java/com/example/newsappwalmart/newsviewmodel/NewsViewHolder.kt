package com.example.newsappwalmart.newsviewmodel

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.newsappwalmart.R

class NewsViewHolder(view: View) {
    val cardView: CardView = view.findViewById(R.id.cardView)
    val titleView: TextView = view.findViewById(R.id.title)
    val author: TextView = view.findViewById(R.id.author)
    val image: ImageView = view.findViewById(R.id.image)
    val descriptionView: TextView = view.findViewById(R.id.description)
    val publishedTimeView: TextView = view.findViewById(R.id.publishedTime)
}