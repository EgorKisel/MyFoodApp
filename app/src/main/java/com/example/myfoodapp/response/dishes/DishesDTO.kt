package com.example.myfoodapp.response.dishes


import com.google.gson.annotations.SerializedName

data class DishesDTO(
    @SerializedName("dishes")
    val dishes: List<Dishe>
)