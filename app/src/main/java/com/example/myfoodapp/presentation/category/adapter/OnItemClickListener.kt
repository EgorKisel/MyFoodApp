package com.example.myfoodapp.presentation.category.adapter

import com.example.myfoodapp.data.model.CategoryKitchenResponse

interface OnItemClickListener {
    fun onItemClick(categoryKitchenResponse: CategoryKitchenResponse)
}