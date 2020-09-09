package com.safiej.stackoverflowapplication.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Rest {

    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.stackexchange.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var soApi = retrofit.create(SOApi::class.java)
}