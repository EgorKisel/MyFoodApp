package com.example.myfoodapp.data.repoimpl

import com.example.myfoodapp.common.BASE_URL
import com.example.myfoodapp.data.model.network.DishesDTO
import com.example.myfoodapp.data.service.DishesApi
import com.example.myfoodapp.domain.RepositoryDishes
import com.example.myfoodapp.presentation.dishes.DishesViewModel
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoryDishesImpl: RepositoryDishes {

    private val api = Retrofit.Builder().apply {
        baseUrl(BASE_URL)
        addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
    }.build().create(DishesApi::class.java)

    override fun getDishes(callbackMy: DishesViewModel.Callback) {
        api.getDishes().enqueue(object : Callback<DishesDTO> {
            override fun onResponse(call: Call<DishesDTO>, response: Response<DishesDTO>) {
                // Во ViewModel
                if (response.isSuccessful) {
                    response.body()?.let {
                        callbackMy.onResponse(it)
                    }
                }
            }

            override fun onFailure(call: Call<DishesDTO>, t: Throwable) {
                // Not yet implemented
            }

        })
    }
}