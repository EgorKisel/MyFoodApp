package com.example.myfoodapp.presentation.basket

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfoodapp.core.MyApp.Companion.getBasketItems
import com.example.myfoodapp.data.model.room.CartItemDbEntity
import com.example.myfoodapp.data.repoimpl.BasketRepositoryImpl
import com.example.myfoodapp.domain.BasketRepository
import com.example.myfoodapp.domain.GetPriceUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class BasketViewModel(
    private val basketRepo: BasketRepository = BasketRepositoryImpl(getBasketItems()),
    private val getPriceUseCase: GetPriceUseCase = GetPriceUseCase(basketRepo)
) : ViewModel() {

    private val basketLiveData: MutableLiveData<AppState> = MutableLiveData()
    fun getLiveData() = basketLiveData

    fun getAllBasket() {
        viewModelScope.launch {
            val items = basketRepo.getAllBasketItems()
            basketLiveData.postValue(AppState.Success(basketRepo.getAllBasketItems()))
        }
    }

    fun calculateTotalPrice(): Double {
        return runBlocking {
            getPriceUseCase.invoke()
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
            basketRepo.updateCartItemQuantity(itemId, quantity)
            getAllBasket()
        }
    }

    fun deleteItemById(itemId: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                basketRepo.deleteCartItemById(itemId)
                getAllBasket()
            }
        }
    }

    sealed class AppState {
        object Loading : AppState()
        data class Success(val basket: List<CartItemDbEntity>) : AppState()
        data class Error(val error: Throwable) : AppState()
    }
}
