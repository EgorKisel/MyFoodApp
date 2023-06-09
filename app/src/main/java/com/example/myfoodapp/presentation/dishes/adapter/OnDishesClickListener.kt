package com.example.myfoodapp.presentation.dishes.adapter

import com.example.myfoodapp.data.model.network.DisheResponse

interface OnDishesClickListener {
    fun onItemClick(dishes: DisheResponse)
}