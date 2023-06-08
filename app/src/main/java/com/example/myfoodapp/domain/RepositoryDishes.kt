package com.example.myfoodapp.domain

import com.example.myfoodapp.presentation.dishes.DishesViewModel

interface RepositoryDishes {
    fun getDishes(callback: DishesViewModel.Callback)
}