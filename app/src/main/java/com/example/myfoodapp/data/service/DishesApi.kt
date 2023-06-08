package com.example.myfoodapp.data.service

import com.example.myfoodapp.commom.DISHES_ENDPOINT
import com.example.myfoodapp.data.model.DishesDTO
import retrofit2.Call
import retrofit2.http.GET

interface DishesApi {
    @GET(DISHES_ENDPOINT)
    fun getDishes(): Call<DishesDTO>
}