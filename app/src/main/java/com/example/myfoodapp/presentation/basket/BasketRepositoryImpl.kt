package com.example.myfoodapp.presentation.basket

import com.example.myfoodapp.data.model.room.CartItemDao
import com.example.myfoodapp.data.model.room.CartItemDbEntity

class BasketRepositoryImpl(private val cartItemDao: CartItemDao) : BasketRepository {
    override suspend fun getAllBasketItems(): List<CartItemDbEntity> {
        return cartItemDao.getAllCartItems()
    }

    override fun insertCartItem(cartItem: CartItemDbEntity) {
        cartItemDao.insertCartItem(cartItem)
    }

    override fun deleteCartItem(cartItem: CartItemDbEntity) {
        cartItemDao.deleteItem(cartItem)
    }

    override suspend fun updateCartItemQuantity(itemId: Long, quantity: Int) {
        cartItemDao.updateCartItemQuantity(itemId, quantity)
    }

    override suspend fun deleteCartItemById(itemId: Long) {
        cartItemDao.deleteCartItemById(itemId)
    }

}