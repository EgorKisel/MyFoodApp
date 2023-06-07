package com.example.myfoodapp.presentation.view.dishes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfoodapp.data.model.DishesResponse
import com.example.myfoodapp.data.repoimpl.RepositoryDishesImpl
import com.example.myfoodapp.domain.RepositoryDishes
import com.example.myfoodapp.presentation.view.category.MainViewModel
import com.google.android.material.chip.Chip

class DishesViewModel(
    private val liveData: MutableLiveData<DishesAppState> = MutableLiveData(),
    private val repository: RepositoryDishes = RepositoryDishesImpl(),
) : ViewModel() {

    private val categoryList = listOf("Все меню", "Категория 1 ", "Категория 2")

    fun getLiveData() = liveData

    fun getDishes(categoryId: Int?) { // categoryId в реальном api пригодиться
        repository.getDishes(callback)
    }

    fun onChipCliked(selectedTag: String?) {
        val category: Int = categoryList.indexOf(selectedTag)
        val value: String = categoryList[category]
        liveData.postValue(MainViewModel.AppState.Success())//Old List
    }

    private val callback = object : Callback {
        override fun onResponse(dishes: DishesResponse) {
            liveData.postValue(DishesAppState.Success(dishes))
        }

        override fun onFail() {
            liveData.postValue(DishesAppState.Error(Throwable()))
        }

    }


    sealed class DishesAppState {
        object Loading : DishesAppState()
        data class Success(val dishes: DishesResponse) : DishesAppState()
        data class Error(val error: Throwable) : DishesAppState()
    }

}