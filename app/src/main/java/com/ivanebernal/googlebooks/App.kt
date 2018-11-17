package com.ivanebernal.googlebooks

import android.app.Application
import com.squareup.picasso.Picasso

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        val picasso = Picasso.Builder(this)
                .loggingEnabled(true)
                .build()
        Picasso.setSingletonInstance(picasso)
    }
}