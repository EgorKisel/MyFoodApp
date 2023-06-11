package com.example.myfoodapp.data.model.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CartItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCartItem(cartItem: CartItemDbEntity)

    @Query("SELECT * FROM cart_items")
    fun getAllCartItems(): List<CartItemDbEntity>

    @Query("Delete from cart_items where id = :id")
    fun deleteCartItemById(id: Long)

    @Delete
    fun deleteItem(cartItem: CartItemDbEntity)

    @Query("UPDATE cart_items SET quantity = :quantity WHERE id = :itemId")
    suspend fun updateCartItemQuantity(itemId: Long, quantity: Int)
}