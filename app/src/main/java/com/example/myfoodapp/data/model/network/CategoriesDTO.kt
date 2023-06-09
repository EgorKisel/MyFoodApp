package com.example.myfoodapp.data.model.network


import com.google.gson.annotations.SerializedName

data class CategoriesDTO(
    @SerializedName("сategories")
    val categories: List<CategoryKitchenResponse>
)