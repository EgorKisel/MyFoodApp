package com.example.myfoodapp.data.model.network


import com.google.gson.annotations.SerializedName

data class CategoryKitchenResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("name")
    val name: String
)