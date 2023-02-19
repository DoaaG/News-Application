package com.example.newsapplication.adapters

import com.example.newsapplication.model.Category

interface OnNewsClick {
    fun newsClick(category: Category, position: Int)
}