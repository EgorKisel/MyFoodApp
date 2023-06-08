package com.example.myfoodapp.model

import com.example.myfoodapp.commom.BASE_URL
import com.example.myfoodapp.response.categories.CategoriesDTO
import com.example.myfoodapp.viewmodel.MainViewModel
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoryImpl : Repository {

    private val api = Retrofit.Builder().apply {
        baseUrl(BASE_URL)
        addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
    }.build().create(CategoriesApi::class.java)

    override fun getCategories(callbackMy: MainViewModel.Callback) {
        api.getCategories().enqueue(object : Callback<CategoriesDTO> {
            override fun onResponse(
                call: Call<CategoriesDTO>,
                response: Response<CategoriesDTO>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callbackMy.onResponse(it)
                    }
                }
            }

            override fun onFailure(call: Call<CategoriesDTO>, t: Throwable) {
                // Not yet implemented
            }

        })
    }
}