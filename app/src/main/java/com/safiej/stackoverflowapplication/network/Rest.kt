package com.safiej.stackoverflowapplication.network

import com.safiej.stackoverflowapplication.network.apis.SOApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Rest {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.stackexchange.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val soApi: SOApi = retrofit.create(SOApi::class.java)
}