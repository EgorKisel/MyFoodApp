package com.example.myfoodapp.data.model.room

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dishes(
    val id: Long,
    val name: String,
    val image: String,
    val price: Int,
    val weight: Int,
    val quantity: Int
): Parcelable
