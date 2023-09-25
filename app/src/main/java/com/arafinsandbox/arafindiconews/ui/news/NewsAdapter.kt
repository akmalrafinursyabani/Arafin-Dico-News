package com.arafinsandbox.arafindiconews.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arafinsandbox.arafindiconews.R
import com.arafinsandbox.arafindiconews.data.news.News

class ListNewsAdapter(private val listNews: ArrayList<News>) :
    RecyclerView.Adapter<ListNewsAdapter.NewsViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: News)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val newsImage: ImageView = itemView.findViewById(R.id.news_image)
        val newsTitle: TextView = itemView.findViewById(R.id.news_title)
        val newsDate: TextView = itemView.findViewById(R.id.news_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.news_layout, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int = listNews.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val (title, image, date) = listNews[position]

        holder.newsImage.setImageResource(image)
        holder.newsTitle.text = title
        holder.newsDate.text = date

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listNews[holder.adapterPosition])
        }

    }

}