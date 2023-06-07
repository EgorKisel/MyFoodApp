package com.example.myfoodapp.response.categories


import com.google.gson.annotations.SerializedName

data class CategoryKitchen(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("name")
    val name: String
)