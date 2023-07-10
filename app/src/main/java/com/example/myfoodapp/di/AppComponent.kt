package com.example.myfoodapp.di

import com.example.myfoodapp.presentation.activity.MainActivity
import com.example.myfoodapp.presentation.activity.MainViewModel
import com.example.myfoodapp.presentation.basket.BasketViewModel
import com.example.myfoodapp.presentation.category.CategoryViewModel
import com.example.myfoodapp.presentation.dishes.DishesViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DatabaseModule::class,
        AppModule::class,
        NavigationModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainViewModel: MainViewModel)
    fun inject(basketViewModel: BasketViewModel)
    fun inject(categoryViewModel: CategoryViewModel)
    fun inject(dishesViewModel: DishesViewModel)
}