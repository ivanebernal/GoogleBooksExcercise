package com.ivanebernal.googlebooks

import com.ivanebernal.googlebooks.models.BookResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksService {
    @GET("volumes")
    fun getBooks(@Query("q") search: String,
                 @Query("startIndex") startIndex: Int,
                 @Query("maxResults") maxResults: Int): Call<BookResponse>

    @GET("volumes/{volumeId}")
    fun getBook(@Path("volumeId") id: String)

}