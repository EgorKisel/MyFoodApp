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
    private val allDishes: MutableList<DisheResponse> = mutableListOf()
) : ViewModel() {

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
        selectedTagLiveData.value = tag
    }
    fun filterDishesByTag() {
        val selectedTag = selectedTagLiveData.value

        if (selectedTag == "Всё меню") {
            // Если выбран чип "Всё меню", показываем все блюда без фильтрации
            dishesLiveData.value = allDishes
        } else {
            // Фильтруем список блюд по выбранному тегу
            val filteredDishes = allDishes.filter { dish ->
                dish.tegs.contains(selectedTag)
            }
            dishesLiveData.value = filteredDishes
        }
    }

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

    sealed class DishesAppState {
        object Loading: DishesAppState()
        data class Success(val dishes: DishesDTO): DishesAppState()
        data class Error(val error: Throwable): DishesAppState()
    }
}