package com.example.myfoodapp.di

import android.content.Context
import com.example.myfoodapp.data.model.room.AppDatabase
import com.example.myfoodapp.data.repoimpl.BasketRepositoryImpl
import com.example.myfoodapp.domain.BasketRepository
import dagger.Module
import dagger.Provides

@Module
object DatabaseModule {

    @Provides
    fun provideDatabase(context: Context): AppDatabase = AppDatabase.create(context)

    @Provides
    fun provideBasketRepository(appDatabase: AppDatabase): BasketRepository =
        BasketRepositoryImpl(appDatabase.cartItemDao())
}