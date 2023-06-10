package com.example.myfoodapp.common

import com.example.myfoodapp.data.model.room.CartItemDbEntity
import com.example.myfoodapp.data.model.room.Dishes

fun convertCartItemToDish(cartItem: CartItemDbEntity): List<Dishes> {
    val cartItemList = listOf(cartItem)
    return cartItemList.map {
        Dishes(it.id, it.itemName, it.itemImage, it.itemPrice, it.itemWeight, it.quantity)
    }
}

fun convertDishToCartItem(dish: Dishes): CartItemDbEntity {
    return CartItemDbEntity(
        dish.id, dish.name, dish.image, dish.price, dish.weight, dish.quantity
    )
}

fun convertDishesToCartItems(dishesList: List<Dishes>): List<CartItemDbEntity> {
    return dishesList.map { dish ->
        CartItemDbEntity(
            dish.id,
            dish.name,
            dish.image,
            dish.price,
            dish.weight,
            dish.quantity
        )
    }
}