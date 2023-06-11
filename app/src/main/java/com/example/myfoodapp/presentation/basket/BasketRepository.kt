package com.example.myfoodapp.presentation.basket

import com.example.myfoodapp.data.model.room.CartItemDbEntity

interface BasketRepository {
    suspend fun getAllBasketItems(): List<CartItemDbEntity>
    fun insertCartItem(cartItem: CartItemDbEntity)
    fun deleteCartItem(cartItem: CartItemDbEntity)
    suspend fun updateCartItemQuantity(itemId: Long, quantity: Int)
    suspend fun deleteCartItemById(itemId: Long)
}