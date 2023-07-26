package com.example.myfoodapp.data.service

import com.example.myfoodapp.common.BASE_ENDPOINT
import com.example.myfoodapp.data.model.network.CategoriesDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET

interface CategoriesApi {
    @GET(BASE_ENDPOINT)
    fun getCategories(): Single<List<CategoriesDTO>>
}