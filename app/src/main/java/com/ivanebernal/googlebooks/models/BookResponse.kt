package com.ivanebernal.googlebooks.models

import com.google.gson.annotations.SerializedName

data class BookResponse(
        @SerializedName("items") val books: List<Book>,
        @SerializedName("totalItems") val totalBooks: Int

)