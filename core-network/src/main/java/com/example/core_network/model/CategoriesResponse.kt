package com.example.core_network.model

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("сategories")
    val categories: List<CategoryKitchenResponse>
)
