package com.javeria.booklisting

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.javeria.booklisting.model.BookDetails

class BooksListAdapter(val bookList: List<BookDetails> , val context: Context ) : BaseAdapter() {

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View? {
        val bookDetails = bookList[position]
        var rootView = view?.rootView
        if (rootView == null) {
            rootView = LayoutInflater.from(context).inflate(R.layout.book_item, null);
        }
        val tvTitle = rootView?.findViewById<TextView>(R.id.tv_title)
        val tvAuthors = rootView?.findViewById<TextView>(R.id.tv_authors)
        val tvRating = rootView?.findViewById<TextView>(R.id.tv_rating)
        val tvPublishedDate = rootView?.findViewById<TextView>(R.id.tv_pub_date)
        val tvPrice = rootView?.findViewById<TextView>(R.id.tv_price)
        tvTitle?.text = bookDetails.title
        val authors = bookDetails.authors
        if (authors != null && authors.size > 0){
            for (author in authors)
                tvAuthors?.text = author
        }
        tvRating?.text = bookDetails.avgRating.toString()
        tvPublishedDate?.text  = bookDetails.datePublished.toString()
        tvPrice?.text = bookDetails.amount.toString()
        return rootView
    }

    override fun getItem(position: Int): Any {
        return bookList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return bookList.size
    }

}