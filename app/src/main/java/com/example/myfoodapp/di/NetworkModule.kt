package com.example.myfoodapp.di

import com.example.myfoodapp.common.BASE_URL
import com.example.myfoodapp.data.service.DishesApi
import com.example.myfoodapp.presentation.basket.RepoApi
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideNewsApi(client: OkHttpClient): DishesApi =
        Retrofit.Builder().baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(DishesApi::class.java)

    @Singleton
    @Provides
    fun client() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build()
//        .addInterceptor { chain ->
//            val request = chain.request()
//            val url = request.url.newBuilder().addQueryParameter(PAGE_SIZE, PAGE_SIZE_COUNT).build()
//            chain.proceed(request.newBuilder().url(url).build())
//        }.build()

    @Provides
    @Singleton
    fun provideRepoNetwork(api: DishesApi): RepoApi {
        return RepoApi(api)
    }
}
