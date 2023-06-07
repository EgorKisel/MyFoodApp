package com.example.myfoodapp.data.model


import com.google.gson.annotations.SerializedName

data class DishesResponse(
    @SerializedName("dishes")
    val dishes: List<DishResponse>
)