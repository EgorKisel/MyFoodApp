package com.example.myfoodapp.data.model.network


import com.google.gson.annotations.SerializedName

data class DishesDTO(
    @SerializedName("dishes")
    val dishes: List<DisheResponse>
)