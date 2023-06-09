package com.example.myfoodapp.data.model.network


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class DisheResponse(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("tegs")
    val tegs: List<String>,
    @SerializedName("weight")
    val weight: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.createStringArrayList() ?: emptyList(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(description)
        parcel.writeInt(id)
        parcel.writeString(imageUrl)
        parcel.writeString(name)
        parcel.writeInt(price)
        parcel.writeStringList(tegs)
        parcel.writeInt(weight)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DisheResponse> {
        override fun createFromParcel(parcel: Parcel): DisheResponse {
            return DisheResponse(parcel)
        }

        override fun newArray(size: Int): Array<DisheResponse?> {
            return arrayOfNulls(size)
        }
    }
}