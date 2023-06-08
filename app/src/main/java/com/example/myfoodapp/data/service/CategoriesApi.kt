package com.example.myfoodapp.data.service

import com.example.myfoodapp.common.BASE_ENDPOINT
import com.example.myfoodapp.data.model.CategoriesDTO
import retrofit2.Call
import retrofit2.http.GET

interface CategoriesApi {
    @GET(BASE_ENDPOINT)
    fun getCategories(): Call<CategoriesDTO>
}