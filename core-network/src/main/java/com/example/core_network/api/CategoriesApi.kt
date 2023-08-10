package com.example.core_network.api

import com.example.core_network.model.CategoriesResponse
import retrofit2.http.GET

interface CategoriesApi {
    @GET("v3/058729bd-1402-4578-88de-265481fd7d54")
    fun getCategories(): CategoriesResponse
}