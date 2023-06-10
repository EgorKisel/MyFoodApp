package com.example.myfoodapp.data.model.room

import androidx.lifecycle.LiveData
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
    fun deleteItemById(id: Int)

    @Delete
    fun deleteItem(cartItem: CartItemDbEntity)
}