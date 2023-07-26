package com.example.myfoodapp.domain

import com.example.myfoodapp.data.model.network.DishesDTO
import io.reactivex.rxjava3.core.Single

interface RepositoryDishes {
    fun getDishes(): Single<List<DishesDTO>>
}