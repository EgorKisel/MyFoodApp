package com.example.myfoodapp.presentation.view.dishes

import com.example.myfoodapp.data.model.DishesResponse

interface Callback {
    fun onResponse(dishes: DishesResponse)
    fun onFail()
}
