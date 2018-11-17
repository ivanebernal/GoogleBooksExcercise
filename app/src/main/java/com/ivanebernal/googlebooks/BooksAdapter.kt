package com.ivanebernal.googlebooks

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ivanebernal.googlebooks.models.Book
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_book.view.*

class BooksAdapter : RecyclerView.Adapter<BooksAdapter.BookHolder>() {

    val books: MutableList<Book> = mutableListOf()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BookHolder {
        val inflater = LayoutInflater.from(p0.context)
        val view = inflater.inflate(R.layout.layout_book, p0, false)
        return BookHolder(view)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(p0: BookHolder, p1: Int) {
        p0.bindView(books[p1])
    }

    fun update(books: List<Book>){
        this.books.clear()
        this.books.addAll(books)
        notifyDataSetChanged()
    }

    class BookHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindView(book: Book){
            Picasso.get().load(book.volumeInfo.imageLinks.thumbnail)
                    .placeholder(android.R.drawable.btn_star_big_on)
                    .into(itemView.book_thumbnail)
            itemView.book_title.text = book.volumeInfo.title
            itemView.published_date.text = book.volumeInfo.publishedDate
        }
    }
}
