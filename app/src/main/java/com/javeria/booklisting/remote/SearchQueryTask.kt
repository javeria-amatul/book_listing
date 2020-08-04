package com.javeria.booklisting.remote

import android.content.Context
import androidx.loader.content.AsyncTaskLoader
import com.javeria.booklisting.Utils
import com.javeria.booklisting.model.BookDetails
import java.net.URL

class SearchQueryTask(val url: URL?, context: Context) :
    AsyncTaskLoader<List<BookDetails>>(context) {

    var bookDetailsList: List<BookDetails>? = null

    override fun onStartLoading() {
        forceLoad()
        super.onStartLoading()
    }

    override fun loadInBackground(): List<BookDetails>? {
        if (url == null)
            return null

        val parseSearchQueryResponse = Utils.getSearchQueryResponse(url)
        if (parseSearchQueryResponse != null) {
            bookDetailsList = AppParser.parseSearchQueryResponse(parseSearchQueryResponse)
        }
        return bookDetailsList
    }
}