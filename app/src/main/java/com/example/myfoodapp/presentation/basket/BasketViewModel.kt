package com.example.myfoodapp.presentation.basket

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfoodapp.data.model.room.CartItemDbEntity

class BasketViewModel(
    val basketLiveData: MutableLiveData<AppState>,
    //private val basketRepo:
) : ViewModel() {

    fun getAllHistory() {
        //basketLiveData.postValue(AppState.Success())
    }

    sealed class AppState {
        object Loading: AppState()
        data class Success(val basket: List<CartItemDbEntity>): AppState()
        data class Error(val error: Throwable): AppState()
    }
}