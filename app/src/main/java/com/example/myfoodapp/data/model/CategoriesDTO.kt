package com.example.myfoodapp.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CategoriesDTO(
    @SerializedName("сategories")
    val categories: List<CategoryKitchen>
)