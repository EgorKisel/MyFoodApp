package com.example.myfoodapp.domain

import com.example.myfoodapp.core.MyApp.Companion.getBasketItems
import com.example.myfoodapp.data.repoimpl.BasketRepositoryImpl

class GetPriceUseCase(private val basketRepo: BasketRepository = BasketRepositoryImpl(getBasketItems())) {
    suspend operator fun invoke(): Double {
        val basketItems = basketRepo.getAllBasketItems()
        var price = 0.0
        basketItems.forEach {
            price += it.itemPrice * it.quantity
        }
        return price
    }
}