package com.javeria.booklisting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SearchView
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import com.javeria.booklisting.model.BookDetails
import com.javeria.booklisting.remote.Constants.endpoint
import com.javeria.booklisting.remote.SearchQueryTask
import java.net.URL

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<List<BookDetails>>, SearchView.OnQueryTextListener {

    private lateinit var booksListView: ListView
    private var bookListAdapter: BooksListAdapter? = null
    private var searchView: SearchView? = null
    private var url: URL? = null
    private lateinit var loader: LoaderManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        booksListView = findViewById(R.id.lv_book_list)
        searchView = findViewById(R.id.sv_search)
        searchView?.setOnQueryTextListener(this)
        loader = LoaderManager.getInstance(this)
        loader?.initLoader(0, null, this)
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<List<BookDetails>> {
        return SearchQueryTask(url, this)
    }

    override fun onLoadFinished(loader: Loader<List<BookDetails>>, data: List<BookDetails>?) {
        if (data != null) {
            bookListAdapter = BooksListAdapter(data, this)
            booksListView.adapter = bookListAdapter
        }
    }

    override fun onLoaderReset(loader: Loader<List<BookDetails>>) {
        bookListAdapter = BooksListAdapter(ArrayList(), this)
    }

    override fun onQueryTextSubmit(text: String?): Boolean {
        url = Utils.createUrl(endpoint+text)
       loader.restartLoader(0,null,this)
        return false
    }

    override fun onQueryTextChange(text: String?): Boolean {
       return false
    }

}