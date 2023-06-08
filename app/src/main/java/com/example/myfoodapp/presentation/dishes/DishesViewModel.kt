package com.example.myfoodapp.presentation.dishes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfoodapp.data.model.DisheResponse
import com.example.myfoodapp.data.model.DishesDTO
import com.example.myfoodapp.data.repoimpl.RepositoryDishesImpl
import com.example.myfoodapp.domain.RepositoryDishes

class DishesViewModel(
    private val liveData: MutableLiveData<DishesAppState> = MutableLiveData(),
    private val repository: RepositoryDishes = RepositoryDishesImpl(),
    private val selectedTagLiveData: MutableLiveData<String> = MutableLiveData(),
    private val dishesLiveData: MutableLiveData<List<DisheResponse>> = MutableLiveData(),
    private val allDishes: MutableList<DisheResponse> = mutableListOf(),
) : ViewModel() {

    //Не вызываем
    fun getSelectedTagLiveData(): LiveData<String> = selectedTagLiveData

    fun getDishesLiveData(): LiveData<List<DisheResponse>> = dishesLiveData

    fun getDishes2() {
        // Загрузка списка блюд
        repository.getDishes(object : Callback {
            override fun onResponse(dishesResponse: DishesDTO) {
                allDishes.clear()
                allDishes.addAll(dishesResponse.dishes)
                dishesLiveData.value = allDishes
            }

            override fun onFail() {
                // Обработка ошибки
            }
        })
    }

    fun onChipSelected(tag: String) {
        //selectedTagLiveData.value = tag
        filterDishesByTag(tag)
    }

    private fun filterDishesByTag(tag: String) {
        val selectedTag = tag
        //Констанкат
        if (selectedTag == "Всё меню") {
            if (allDishes.isEmpty()) {
                liveData.postValue(DishesAppState.EmptyList)
                return
            }
            dishesLiveData.value = allDishes
        } else {
            // Фильтруем список блюд по выбранному тегу
            val filteredDishes: List<DisheResponse> = allDishes.filter { dish ->
                dish.tegs.contains(selectedTag)
            }
            if (filteredDishes.isEmpty()) {
                liveData.postValue(DishesAppState.EmptyList)
                return
            }
            dishesLiveData.value = filteredDishes
        }
    }

    fun getLiveData() = liveData

    fun getDishes() {
        repository.getDishes(callback)
    }


    //Callback поменять
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

    sealed class DishesAppState {
        data class Success(val dishes: DishesDTO) : DishesAppState()
        data class Error(val error: Throwable) : DishesAppState()
        object Loading : DishesAppState()
        object EmptyList : DishesAppState()
    }
}