package com.example.myfoodapp.data.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItemDbEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val itemName: String,
    val itemImage: String,
    val itemPrice: Int,
    val itemWeight: Int,
    var quantity: Int = 1
)
