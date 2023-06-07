package com.example.myfoodapp.response.categories


import com.google.gson.annotations.SerializedName

data class CategoriesDTO(
    @SerializedName("сategories")
    val categories: List<CategoryKitchen>
)