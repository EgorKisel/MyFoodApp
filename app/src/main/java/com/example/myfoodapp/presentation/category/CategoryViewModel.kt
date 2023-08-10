package com.example.myfoodapp.presentation.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfoodapp.common.screen.DishesScreen
import com.example.myfoodapp.data.model.network.CategoriesDTO
import com.example.myfoodapp.data.model.network.CategoryKitchenResponse
import com.example.myfoodapp.data.repoimpl.RepositoryCategoryImpl
import com.example.myfoodapp.domain.RepositoryCategory
import com.github.terrakok.cicerone.Router

class CategoryViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryCategory: RepositoryCategory = RepositoryCategoryImpl(),
    private val router: Router
) : ViewModel() {

    fun getLiveData() = liveData

    fun getCategories() {
        repositoryCategory.getCategories(callback)
    }

    private val api = repositoryCategory

    fun openDishesScreen(categoryKitchenResponse: CategoryKitchenResponse) {
        router.navigateTo(DishesScreen(categoryKitchenResponse))
    }

    private val callback = object : Callback {

        override fun onResponse(category: CategoriesDTO) {
            liveData.postValue(AppState.Success(category))
        }

        override fun onFail() {
            liveData.postValue(AppState.Error(Throwable()))
        }

    }

    interface Callback {
        fun onResponse(category: CategoriesDTO)
        fun onFail()
    }

    sealed class AppState {
        object Loading: AppState()
        data class Success(val categoryDTO: CategoriesDTO): AppState()
        data class Error(val error: Throwable): AppState()
    }
}