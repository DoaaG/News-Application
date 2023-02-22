package com.example.newsapplication.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.newsapplication.R

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView,url:String){
    Glide.with(imageView)
        .load(url)
//        .placeholder(R.drawable.ic_image)
        .into(imageView)
}