package com.example.newsapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapplication.databinding.ArticleItemBinding
import com.example.newsapplication.model.ArticlesItem

class ArticlesAdapter (var articlesList : List<ArticlesItem?>): RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder>() {
    inner class ArticlesViewHolder(var binding :ArticleItemBinding):RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        var bind = ArticleItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArticlesViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        var items = articlesList[position]
        holder.binding.articleAuthor.text = items?.author
        holder.binding.articleDescription.text = items?.description

        // to download image
        Glide.with(holder.binding.root).load(items?.url)
            .into(holder.binding.itemImageInCardview)


    }

    override fun getItemCount() = articlesList.size

    fun changeData(newlist : List<ArticlesItem?>){
        articlesList = newlist
        notifyDataSetChanged()

    }

}