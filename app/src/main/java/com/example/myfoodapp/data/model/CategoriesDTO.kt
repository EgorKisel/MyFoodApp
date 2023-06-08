package com.example.myfoodapp.data.model


import com.google.gson.annotations.SerializedName

data class CategoriesDTO(
    @SerializedName("сategories")
    val categories: List<CategoryKitchenResponse>
)