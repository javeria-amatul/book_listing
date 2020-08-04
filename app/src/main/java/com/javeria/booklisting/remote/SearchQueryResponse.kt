package com.javeria.booklisting.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


public class SearchQueryResponse {

    @SerializedName("kind")
    @Expose
    var kind: String = ""

    @SerializedName("totalItems")
    @Expose
    var totalItems: Int = 0

    @SerializedName("items")
    @Expose
    var items: List<Item> = ArrayList()
}

public class Item {

    @SerializedName("kind")
    @Expose
    var kind: String = ""

    @SerializedName("id")
    @Expose
    var id: String = ""

    @SerializedName("volumeInfo")
    @Expose
    var volumeInfo: VolumeInfo? = null

    @SerializedName("saleInfo")
    @Expose
    var saleInfo: SaleInfo? = null

}

public class VolumeInfo {

    @SerializedName("title")
    @Expose
    var title: String = ""

    @SerializedName("authors")
    @Expose
    var authors: List<String> = ArrayList();

    @SerializedName("publisher")
    @Expose
    var publisher: String = ""

    @SerializedName("publishedDate")
    @Expose
    var publishedDate: String = ""

    @SerializedName("description")
    @Expose
    var description: String = ""

    @SerializedName("pageCount")
    @Expose
    var pageCount: Int = 0

    @SerializedName("averageRating")
    @Expose
    var averageRating: Int = 0

    @SerializedName("language")
    @Expose
    var language: String = ""
}

public class SaleInfo {

    @SerializedName("country")
    @Expose
    var country: String = "";

    @SerializedName("saleability")
    @Expose
    var saleability: String = ""

    @SerializedName("retailPrice")
    @Expose
    var retailPrice: RetailPrice? = null

    @SerializedName("buyLink")
    @Expose
    var buyLink: String = ""
}

public class RetailPrice {

    @SerializedName("amount")
    @Expose
    var amount: Float = 0.0f

    @SerializedName("currencyCode")
    @Expose
    var currencyCode: String = ""

}





