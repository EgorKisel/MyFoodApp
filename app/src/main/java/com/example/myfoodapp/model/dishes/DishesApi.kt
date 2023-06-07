package com.example.myfoodapp.model.dishes

import com.example.myfoodapp.DISHES_ENDPOINT
import com.example.myfoodapp.response.dishes.DishesDTO
import retrofit2.Call
import retrofit2.http.GET

interface DishesApi {
    @GET(DISHES_ENDPOINT)
    fun getDishes(): Call<DishesDTO>
}