package com.example.myfoodapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfoodapp.model.Repository
import com.example.myfoodapp.model.RepositoryImpl
import com.example.myfoodapp.response.categories.CategoriesDTO

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

    interface Callback {
        fun onResponse(category: CategoriesDTO)
        fun onFail()
    }
}