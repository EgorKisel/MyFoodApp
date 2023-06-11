package com.example.myfoodapp.presentation.basket

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfoodapp.MyApp.Companion.getBasketItems
import com.example.myfoodapp.data.model.room.CartItemDbEntity
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
                    basketLiveData.postValue(AppState.Success(cartItems))
                }
            }
        }
    }

    fun insertItem(cartItemDbEntity: CartItemDbEntity) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                basketRepo.insertCartItem(cartItemDbEntity)
            }
        }
    }

    fun deleteItem(cartItemDbEntity: CartItemDbEntity) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                basketRepo.deleteCartItem(cartItemDbEntity)
            }
        }
    }

    fun updateCartItemQuantity(itemId: Long, quantity: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                basketRepo.updateCartItemQuantity(itemId, quantity)
            }
        }
    }

    fun deleteItemById(itemId: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                basketRepo.deleteCartItemById(itemId)
            }
        }
    }

    sealed class AppState {
        object Loading : AppState()
        data class Success(val basket: List<CartItemDbEntity>) : AppState()
        data class Error(val error: Throwable) : AppState()
    }
}
