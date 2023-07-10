package com.example.myfoodapp.di

import com.example.myfoodapp.common.BASE_URL
import com.example.myfoodapp.common.PAGE_SIZE
import com.example.myfoodapp.common.PAGE_SIZE_COUNT
import com.example.myfoodapp.data.service.CategoriesApi
import com.example.myfoodapp.data.service.DishesApi
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideCategoriesApi(client: OkHttpClient): CategoriesApi =
        Retrofit.Builder().baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(CategoriesApi::class.java)

    @Singleton
    @Provides
    fun provideDishesApi(client: OkHttpClient): DishesApi =
        Retrofit.Builder().baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(DishesApi::class.java)

    @Singleton
    @Provides
    fun client() = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request()
            val url = request.url.newBuilder().addQueryParameter(PAGE_SIZE, PAGE_SIZE_COUNT).build()
            chain.proceed(request.newBuilder().url(url).build())
        }.build()
}