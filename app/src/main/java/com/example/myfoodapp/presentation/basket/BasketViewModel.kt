package com.example.myfoodapp.presentation.basket

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfoodapp.core.MyApp.Companion.getBasketItems
import com.example.myfoodapp.data.model.network.CategoriesDTO
import com.example.myfoodapp.data.model.network.DishesDTO
import com.example.myfoodapp.data.model.room.CartItemDbEntity
import com.example.myfoodapp.data.repoimpl.BasketRepositoryImpl
import com.example.myfoodapp.data.service.DishesApi
import com.example.myfoodapp.domain.BasketRepository
import com.example.myfoodapp.domain.GetPriceUseCase
import com.example.myfoodapp.presentation.category.CategoryViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

//class BasketViewModel @Inject constructor(
//    private val basketRepo: BasketRepository = BasketRepositoryImpl(getBasketItems()),
//    private val getPriceUseCase: GetPriceUseCase = GetPriceUseCase(basketRepo)
//) : ViewModel() {
//
//    private val basketLiveData: MutableLiveData<AppState> = MutableLiveData()
//    fun getLiveData() = basketLiveData
//
//    fun getAllBasket() {
//        viewModelScope.launch {
//            val items = basketRepo.getAllBasketItems()
//            basketLiveData.postValue(AppState.Success(basketRepo.getAllBasketItems()))
//        }
//    }
//
//    fun calculateTotalPrice(): Double {
//        return runBlocking {
//            getPriceUseCase.invoke()
//        }
//    }
//
//    fun insertItem(cartItemDbEntity: CartItemDbEntity) {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                basketRepo.insertCartItem(cartItemDbEntity)
//            }
//        }
//    }
//
//    fun deleteItem(cartItemDbEntity: CartItemDbEntity) {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                basketRepo.deleteCartItem(cartItemDbEntity)
//            }
//        }
//    }
//
//    fun updateCartItemQuantity(itemId: Long, quantity: Int) {
//        viewModelScope.launch {
//            basketRepo.updateCartItemQuantity(itemId, quantity)
//            getAllBasket()
//        }
//    }
//
//    fun deleteItemById(itemId: Long) {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                basketRepo.deleteCartItemById(itemId)
//                getAllBasket()
//            }
//        }
//    }
//
//    sealed class AppState {
//        object Loading : AppState()
//        data class Success(val basket: List<CartItemDbEntity>) : AppState()
//        data class Error(val error: Throwable) : AppState()
//    }
//}

class RepoApi(private val api: DishesApi) {
    fun getDishes() = api.getDishes()
}


class BasketViewModel @Inject constructor(private val repoApi: RepoApi) : ViewModel() {

    private val basketLiveData: MutableLiveData<AppState> = MutableLiveData()
    fun getLiveData() = basketLiveData

//    fun getAllBasket() {
//        viewModelScope.launch {
//            val items = basketRepo.getAllBasketItems()
//            basketLiveData.postValue(AppState.Success(basketRepo.getAllBasketItems()))
//        }
//    }
//
//    fun calculateTotalPrice(): Double {
//        return runBlocking {
//            getPriceUseCase.invoke()
//        }
//    }



    private val callback = object : Call<DishesDTO> {
        override fun clone(): Call<DishesDTO> {
            TODO("Not yet implemented")
        }

        override fun execute(): Response<DishesDTO> {
            TODO("Not yet implemented")
        }

        override fun isExecuted(): Boolean {
            TODO("Not yet implemented")
        }

        override fun cancel() {
            TODO("Not yet implemented")
        }

        override fun isCanceled(): Boolean {
            TODO("Not yet implemented")
        }

        override fun request(): Request {
            TODO("Not yet implemented")
        }

        override fun timeout(): Timeout {
            TODO("Not yet implemented")
        }

        override fun enqueue(callback: retrofit2.Callback<DishesDTO>) {
            TODO("Not yet implemented")
        }


    }

    fun getApi() {
        repoApi.getDishes()
    }

    init {
        getApi()
    }

    interface Callback {
        fun onResponse(category: DishesDTO)
        fun onFail()
    }

    sealed class AppState {
        object Loading : AppState()
        data class Success(val basket: List<CartItemDbEntity>) : AppState()
        data class Error(val error: Throwable) : AppState()
    }
}
