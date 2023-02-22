package com.example.newsapplication.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapplication.databinding.ArticleItemBinding
import com.example.newsapplication.model.ArticlesItem
import com.example.newsapplication.ui.CategoriesFragment
import com.example.newsapplication.ui.NewsFragment

class ArticlesAdapter (var articlesList : List<ArticlesItem?>): RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder>() {
    inner class ArticlesViewHolder(var binding :ArticleItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(article :ArticlesItem?){
            binding.article = article
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        var bind = ArticleItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArticlesViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        var items = articlesList[position]
          holder.bind(items)   // for binding

    }

    override fun getItemCount() = articlesList.size

    fun changeData(newlist : List<ArticlesItem?>){
        articlesList = newlist
        notifyDataSetChanged()

    }

}