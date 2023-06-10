package com.example.myfoodapp.presentation.basket

import com.example.myfoodapp.data.model.room.CartItemDao
import com.example.myfoodapp.data.model.room.CartItemDbEntity

class BasketRepositoryImpl(private val cartItemDao: CartItemDao) : BasketRepository {
    override fun getAllBasketItems(callback: (List<CartItemDbEntity>) -> Unit) {
        val basketItems = cartItemDao.getAllCartItems()
        callback(basketItems)
    }

    override fun insertCartItem(cartItem: CartItemDbEntity) {
        cartItemDao.insertCartItem(cartItem)
    }

    override fun deleteCartItem(cartItem: CartItemDbEntity) {
        cartItemDao.deleteItem(cartItem)
    }

}