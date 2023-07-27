package com.example.myfoodapp.di

import com.example.myfoodapp.presentation.activity.MainActivity
import com.example.myfoodapp.presentation.basket.FragmentBasket
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(basketFragment: FragmentBasket)
}
