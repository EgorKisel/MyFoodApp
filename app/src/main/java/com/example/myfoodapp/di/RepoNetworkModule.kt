package com.example.myfoodapp.di

import com.example.myfoodapp.data.repoimpl.RepositoryCategoryImpl
import com.example.myfoodapp.data.repoimpl.RepositoryDishesImpl
import com.example.myfoodapp.domain.RepositoryCategory
import com.example.myfoodapp.domain.RepositoryDishes
import dagger.Provides
import javax.inject.Singleton

object RepoNetworkModule {

    @Singleton
    @Provides
    fun provideRepoCategory(): RepositoryCategory {
        return RepositoryCategoryImpl()
    }

    @Singleton
    @Provides
    fun provideRepoDishes(): RepositoryDishes {
        return RepositoryDishesImpl()
    }
}