package com.example.myfoodapp.model.dishes

import com.example.myfoodapp.viewmodel.DishesViewModel

interface RepositoryDishes {
    fun getDishes(callback: DishesViewModel.Callback)
}