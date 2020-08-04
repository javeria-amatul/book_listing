package com.javeria.booklisting.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BookDetails() : Parcelable {

    @SerializedName("title")
    var title: String = ""

    @SerializedName("author")
    var authors:  List<String>? = null

    @SerializedName("datePublished")
    var datePublished: String = ""

    @SerializedName("avgRating")
    var avgRating: Int = 0

    @SerializedName("amount")
    var amount: Float = 0.0f

    @SerializedName("currencyCode")
    var currencyCode: String = ""

    constructor(parcel: Parcel) : this() {
        title = parcel.readString().toString()
        authors = parcel.createStringArrayList()
        datePublished = parcel.readString().toString()
        avgRating = parcel.readInt()
        amount = parcel.readFloat()
        currencyCode = parcel.readString().toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeStringList(authors)
        parcel.writeString(datePublished)
        parcel.writeInt(avgRating)
        parcel.writeFloat(amount)
        parcel.writeString(currencyCode)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BookDetails> {
        override fun createFromParcel(parcel: Parcel): BookDetails {
            return BookDetails(parcel)
        }

        override fun newArray(size: Int): Array<BookDetails?> {
            return arrayOfNulls(size)
        }
    }

}