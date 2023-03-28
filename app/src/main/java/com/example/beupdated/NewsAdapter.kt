package com.example.beupdated

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(var context:Context,var articles:List<ArticlesDataModel>,var click:clicked):
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    lateinit var article:ArticlesDataModel
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.news_itemview,parent,false)
        var viewHolder = NewsViewHolder(view)
        view.setOnClickListener({
            click.itemclicked(articles[viewHolder.adapterPosition])
        })
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        article = articles[position]
        holder.newstitle.text = article.title
        if(article.description==null){
            holder.newsDescrip.text = "No Description"
        }
        else{
            holder.newsDescrip.text = article.description
        }
//        holder.newsDescrip.text = article.description
        if(article.urlToImage==null){
//            holder.newsimage.setImageDrawable(R.drawable.noimage)
            Glide.with(context).load(R.drawable.noimage).into(holder.newsimage)
        }
        else {
            Glide.with(context).load(article.urlToImage).into(holder.newsimage)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class NewsViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var newsDescrip = itemView.findViewById<TextView>(R.id.newsDescription)
        var newsimage = itemView.findViewById<ImageView>(R.id.newsImage)
        var newstitle = itemView.findViewById<TextView>(R.id.NewsTitle)
    }
}
