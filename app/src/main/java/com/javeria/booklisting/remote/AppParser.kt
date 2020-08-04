package com.javeria.booklisting.remote

import android.util.Log
import com.google.gson.JsonObject
import com.javeria.booklisting.model.BookDetails
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

object AppParser {

    const val TAG = "AppParser"
    fun parseSearchQueryResponse(jsonResponse: String): List<BookDetails>? {
        val bookDetailsList = ArrayList<BookDetails>()
        val authorsList = ArrayList<String>()
        try {
            val response = JSONObject(jsonResponse)
            val items = response.optJSONArray("items")
            for (item in 0 until items.length()) {
                val book = items[item] as JSONObject
                val volumeInfo = book.optJSONObject("volumeInfo")
                val authors = volumeInfo.optJSONArray("authors")
                for (author in 0 until authors.length()) {
                    authorsList?.add(authors.get(author) as String)
                }
                val saleInfo = book.optJSONObject("saleInfo")
                val retailPrice = saleInfo.optJSONObject("retailPrice")
                val bookDetails = BookDetails()
                bookDetails.title = volumeInfo.optString("title").toString()
                bookDetails.authors = authorsList
                bookDetails.datePublished = volumeInfo.optString("publishedDate").toString()
                bookDetails.currencyCode = retailPrice.optString("currencyCode").toString()
                bookDetails.amount = (retailPrice.optDouble("amount")).toFloat()
                bookDetails.avgRating = volumeInfo.optInt("averageRating")
                bookDetailsList?.add(bookDetails)
            }
        } catch (ex: Exception) {
            Log.e(TAG, ex.toString())
        }
        return bookDetailsList
    }
}