package com.example.myfoodapp.di

import com.example.myfoodapp.data.repoimpl.RepositoryCategoryImpl
import com.example.myfoodapp.data.service.CategoriesApi
import com.example.myfoodapp.domain.RepositoryCategory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object CategoryModule {

    @Singleton
    @Provides
    fun provideRepositoryCategory(categoriesApi: CategoriesApi): RepositoryCategory {
        return RepositoryCategoryImpl(categoriesApi)
    }
}