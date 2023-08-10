package com.example.core_network.model

import com.google.gson.annotations.SerializedName

data class CategoryKitchenResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("name")
    val name: String
)
