package com.example.myfoodapp.di

import com.example.myfoodapp.data.repoimpl.RepositoryCategoryImpl
import com.example.myfoodapp.data.repoimpl.RepositoryDishesImpl
import com.example.myfoodapp.data.service.CategoriesApi
import com.example.myfoodapp.data.service.DishesApi
import com.example.myfoodapp.domain.RepositoryCategory
import com.example.myfoodapp.domain.RepositoryDishes
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepoNetworkModule {

    @Singleton
    @Provides
    fun provideRepoCategory(categoriesApi: CategoriesApi): RepositoryCategory {
        return RepositoryCategoryImpl(categoriesApi)
    }

    @Singleton
    @Provides
    fun provideRepoDishes(dishesApi: DishesApi): RepositoryDishes {
        return RepositoryDishesImpl(dishesApi)
    }
}