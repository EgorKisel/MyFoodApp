package com.example.myfoodapp.data.service

import com.example.myfoodapp.common.Const.DISHES_ENDPOINT
import com.example.myfoodapp.data.model.DishesResponse
import retrofit2.Call
import retrofit2.http.GET

interface DishesApi {
    @GET(DISHES_ENDPOINT)
    fun getDishes(): Call<DishesResponse>
}