package com.example.myfoodapp.data.service

import com.example.myfoodapp.common.DISHES_ENDPOINT
import com.example.myfoodapp.data.model.network.DishesDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface DishesApi {
    @GET(DISHES_ENDPOINT)
    fun getDishes(): Single<List<DishesDTO>>
}