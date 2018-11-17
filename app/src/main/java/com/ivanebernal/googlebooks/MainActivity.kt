package com.ivanebernal.googlebooks

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.ivanebernal.googlebooks.models.BookResponse
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    val adapter = BooksAdapter()
    val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val retrofit = Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/books/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(interceptor).build())
            .build()
    val bookService = retrofit.create(BooksService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        book_list.layoutManager = LinearLayoutManager(this)
        book_list.adapter = adapter

        search_box.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE){
                val query = v.text.toString()
                if (!query.isEmpty()) searchBooks(query)
            }
            true
        }

    }

    fun searchBooks(query: String){
        progressBar.visibility = View.VISIBLE
        bookService.getBooks(query, 0, 10)
                .enqueue(object : Callback<BookResponse>{
                    override fun onFailure(call: Call<BookResponse>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "Failed!", Toast.LENGTH_LONG).show()
                        progressBar.visibility = View.GONE
                    }

                    override fun onResponse(call: Call<BookResponse>, response: Response<BookResponse>) {
                        adapter.update(response.body()?.books ?: listOf())
                        progressBar.visibility = View.GONE
                    }
                })
    }

}
