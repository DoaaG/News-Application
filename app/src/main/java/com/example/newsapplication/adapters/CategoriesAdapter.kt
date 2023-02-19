package com.example.newsapplication.adapters

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.R
import com.example.newsapplication.model.Category
import com.google.android.material.card.MaterialCardView

class CategoriesAdapter(var list: List<Category>) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {
    inner class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val categoryTitle: TextView = view.findViewById(R.id.category_name)
        val categoryImage: ImageView = view.findViewById(R.id.imageView_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(viewTypeIndex(viewType), parent, false)
        return CategoriesViewHolder(view)
    }
    var onNewsClickListener: OnNewsClick? = null
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val item = list[position]
        val cardBackground = holder.itemView as MaterialCardView
        holder.categoryImage.setImageResource(item.imageId)
        cardBackground.setBackgroundColor(cardBackground.context.getColor(item.colorId))
        holder.categoryTitle.text = item.title

        holder.categoryImage.setOnClickListener{
            onNewsClickListener!!.newsClick(item,position)
        }
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        var categoryPosition = list[position]

        return if (categoryPosition.isLeftSided)
            1
        else
            2
    }

    fun viewTypeIndex(viewType: Int): Int {
        var item = -1
        if (viewType == 1)
            item = R.layout.left_item
        else if (viewType == 2)
            item = R.layout.right_item
        Log.e("viewTypeIndex", viewType.toString())
        return item
    }

}
