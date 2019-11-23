package com.appat.ecamp.ApplicationModules.Dashboard.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.appat.ecamp.ApplicationModules.Dashboard.Models.LatestNews
import com.appat.ecamp.R
import com.bumptech.glide.Glide
import org.jetbrains.anko.find

class LatestNewsAdapter (private val children : List<LatestNews>, private val context: Context)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.latest_news_item, parent, false)
        return LatestNewsItemViewHolder(v)

    }

    override fun getItemCount(): Int {
        return children.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemViewHolder = holder as LatestNewsItemViewHolder
        val newsItem = children[position]
        itemViewHolder.textTitle.text = newsItem.title
        itemViewHolder.textDesc.text = newsItem.description
        Glide.with(context).load(newsItem.imgUrl).into(itemViewHolder.newsImage)
    }

    inner class LatestNewsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newsImage= itemView.find<ImageView>(R.id.newsImage)
        val textTitle= itemView.find<TextView>(R.id.textTitle)
        val textDesc= itemView.find<TextView>(R.id.textDesc)
    }
}