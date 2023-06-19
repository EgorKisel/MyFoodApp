package com.example.myfoodapp.presentation.dishes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfoodapp.common.ALL_MENU
import com.example.myfoodapp.common.screen.CategoryScreen
import com.example.myfoodapp.data.model.network.DisheResponse
import com.example.myfoodapp.data.model.network.DishesDTO
import com.example.myfoodapp.data.repoimpl.RepositoryDishesImpl
import com.example.myfoodapp.domain.RepositoryDishes
import com.github.terrakok.cicerone.Router

class DishesViewModel(
    private val liveData: MutableLiveData<DishesAppState> = MutableLiveData(),
    private val repository: RepositoryDishes = RepositoryDishesImpl(),
    private val dishesLiveData: MutableLiveData<List<DisheResponse>> = MutableLiveData(),
    private val allDishes: MutableList<DisheResponse> = mutableListOf(),
    private val router: Router
) : ViewModel() {

    fun getDishesLiveData(): LiveData<List<DisheResponse>> = dishesLiveData

    fun getDishes() {
        // Загрузка списка блюд
        repository.getDishes(object : Callback {
            override fun onResponse(dishesResponse: DishesDTO) {
                allDishes.clear()
                allDishes.addAll(dishesResponse.dishes)
                dishesLiveData.value = allDishes
            }

            override fun onFail() {
                liveData.postValue(DishesAppState.Error(Throwable()))
            }
        })
    }

    fun onBackPressed(): Boolean {
        router.backTo(CategoryScreen)
        return true
    }

    fun filterDishesByTag(tag: String) {
        if (tag == ALL_MENU) {
            if (allDishes.isEmpty()) {
                liveData.postValue(DishesAppState.EmptyList)
                return
            }
            // Если выбран чип "Всё меню", показываем все блюда без фильтрации
            dishesLiveData.value = allDishes
        } else {
            // Фильтруем список блюд по выбранному тегу
            val filteredDishes = allDishes.filter { dish ->
                dish.tegs.contains(tag)
            }
            if (filteredDishes.isEmpty()) {
                liveData.postValue(DishesAppState.EmptyList)
                return
            }
            dishesLiveData.value = filteredDishes
        }
    }

    fun getLiveData() = liveData

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