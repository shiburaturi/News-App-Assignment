package com.example.newsappwalmart.newsviewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.example.newsappwalmart.R
import com.example.newsappwalmart.newsmodel.NewsData

class NewsAdapter(private val listener: (NewsData) -> Unit) : BaseAdapter() {

    private val items: ArrayList<NewsData> = ArrayList()

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): NewsData {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: NewsViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(parent?.context).inflate(R.layout.news_card, parent, false)
            viewHolder = NewsViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as NewsViewHolder
        }

        val currentItem = getItem(position)
        viewHolder.titleView.text = currentItem.title
        viewHolder.author.text = "Source - ${currentItem.author}"
        viewHolder.descriptionView.text = currentItem.description
        viewHolder.publishedTimeView.text = currentItem.publishedTime

        if(currentItem.title == "[Removed]") {
            viewHolder.image.setImageResource(R.drawable.defaultimage)
        } else if(currentItem.imageUrl.isNotEmpty()) {
            Glide.with(viewHolder.image.context)
                .load(currentItem.imageUrl)
                .into(viewHolder.image)
        }
        // Set an onClick listener for the ListView item
        viewHolder.cardView.setOnClickListener {
            listener(currentItem)
        }
        return view
    }

    fun updateNews(updatedNews: ArrayList<NewsData>) {
        items.clear()
        items.addAll(updatedNews)
        notifyDataSetChanged()
    }
}