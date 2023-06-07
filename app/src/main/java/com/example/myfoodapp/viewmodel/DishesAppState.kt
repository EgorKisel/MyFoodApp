package com.example.myfoodapp.viewmodel

import com.example.myfoodapp.response.dishes.DishesDTO

sealed class DishesAppState {
    object Loading: DishesAppState()
    data class Success(val dishes: DishesDTO): DishesAppState()
    data class Error(val error: Throwable): DishesAppState()
}
