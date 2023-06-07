package com.example.myfoodapp.domain

import com.example.myfoodapp.presentation.view.dishes.DishesViewModel

interface RepositoryDishes {
    fun getDishes(callback: DishesViewModel.Callback)
}