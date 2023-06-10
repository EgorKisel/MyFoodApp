package com.example.myfoodapp.presentation.basket

import com.example.myfoodapp.data.model.room.CartItemDbEntity

interface OnItemClickListener {
    fun onAddToBasket(cartItem: CartItemDbEntity)
    fun onRemoveFromBasket(cartItem: CartItemDbEntity)
}