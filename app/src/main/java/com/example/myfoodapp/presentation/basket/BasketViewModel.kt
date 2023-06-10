package com.example.myfoodapp.presentation.basket

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfoodapp.MyApp.Companion.getBasketItems
import com.example.myfoodapp.common.convertCartItemToDish
import com.example.myfoodapp.common.convertDishToCartItem
import com.example.myfoodapp.data.model.room.Dishes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BasketViewModel(
    private val basketRepo: BasketRepository = BasketRepositoryImpl(getBasketItems())
) : ViewModel() {

    private val basketLiveData: MutableLiveData<AppState> = MutableLiveData()
    fun getLiveData() = basketLiveData

    fun getAllBasket() {
        viewModelScope.launch {
            basketLiveData.value = AppState.Loading
            val basketItems = withContext(Dispatchers.IO) {
                basketRepo.getAllBasketItems { cartItems ->
                    val dishList = cartItems.flatMap { convertCartItemToDish(it) }
                    basketLiveData.postValue(AppState.Success(dishList))
                }
            }
        }
    }

    fun insertItem(dish: Dishes) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                basketRepo.insertCartItem(convertDishToCartItem(dish))
            }
        }
    }

    fun deleteItem(dish: Dishes) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                basketRepo.deleteCartItem(convertDishToCartItem(dish))
            }
        }
    }

    sealed class AppState {
        object Loading : AppState()
        data class Success(val basket: List<Dishes>) : AppState()
        data class Error(val error: Throwable) : AppState()
    }
}