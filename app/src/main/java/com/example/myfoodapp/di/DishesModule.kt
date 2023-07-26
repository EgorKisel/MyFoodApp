package com.example.myfoodapp.di

import com.example.myfoodapp.data.repoimpl.RepositoryDishesImpl
import com.example.myfoodapp.data.service.DishesApi
import com.example.myfoodapp.domain.RepositoryDishes
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DishesModule {

    @Singleton
    @Provides
    fun provideRepositoryDishes(dishesApi: DishesApi): RepositoryDishes {
        return RepositoryDishesImpl(dishesApi)
    }
}