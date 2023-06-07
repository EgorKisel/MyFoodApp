package com.example.myfoodapp.presentation.view.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfoodapp.data.model.CategoriesDTO
import com.example.myfoodapp.data.repoimpl.RepositoryImpl
import com.example.myfoodapp.domain.Repository

class MainViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: Repository = RepositoryImpl()
) : ViewModel() {

    fun getLiveData() = liveData

    fun getCategories() {
        repository.getCategories(callback)
    }

    private val callback = object : Callback {

        override fun onResponse(category: CategoriesDTO) {
            liveData.postValue(AppState.Success(category))
        }

        override fun onFail() {
            liveData.postValue(AppState.Error(Throwable()))
        }

    }



    sealed class AppState {
        object Loading: AppState()
        data class Success(val categoryDTO: CategoriesDTO): AppState()
        data class Error(val error: Throwable): AppState()
    }
}