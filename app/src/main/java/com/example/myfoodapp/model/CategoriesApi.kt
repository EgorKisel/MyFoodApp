package com.example.myfoodapp.model

import com.example.myfoodapp.BASE_ENDPOINT
import com.example.myfoodapp.response.categories.CategoriesDTO
import retrofit2.Call
import retrofit2.http.GET

interface CategoriesApi {
    @GET(BASE_ENDPOINT)
    fun getCategories(): Call<CategoriesDTO>
}