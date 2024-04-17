package com.example.newsapi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class NewsAdapter (val context: Context, val articles: List<Article>) : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var newsImage = itemView.findViewById<ImageView>(R.id.newsImage)
        var newsTitle = itemView.findViewById<TextView>(R.id.newsTitle)
        var newsDescription = itemView.findViewById<TextView>(R.id.newsDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return ArticleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article: Article = articles[position]
        holder.newsTitle.text = article.title
        holder.newsDescription.text = article.description
        Picasso.get().load(article.urlToImage).into(holder.newsImage)
        holder.itemView.setOnClickListener{
            val intent = Intent(context,DetailActivity::class.java)
            intent.putExtra("URL",article.url)
            context.startActivity(intent)
        }
    }
}
