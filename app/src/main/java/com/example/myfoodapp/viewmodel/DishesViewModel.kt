package com.example.myfoodapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfoodapp.model.dishes.RepositoryDishes
import com.example.myfoodapp.model.dishes.RepositoryDishesImpl
import com.example.myfoodapp.response.dishes.DishesDTO

class DishesViewModel(
    private val liveData: MutableLiveData<DishesAppState> = MutableLiveData(),
    private val repository: RepositoryDishes = RepositoryDishesImpl()
) : ViewModel() {

    fun getLiveData() = liveData
    fun getDishes() {
        repository.getDishes(callback)
    }

    private val callback = object : Callback {
        override fun onResponse(dishes: DishesDTO) {
            liveData.postValue(DishesAppState.Success(dishes))
        }

        override fun onFail() {
            liveData.postValue(DishesAppState.Error(Throwable()))
        }

    }

    interface Callback {
        fun onResponse(dishes: DishesDTO)
        fun onFail()
    }
}