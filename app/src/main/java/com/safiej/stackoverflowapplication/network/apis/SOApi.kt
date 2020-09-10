package com.safiej.stackoverflowapplication.network.apis

import com.safiej.stackoverflowapplication.network.responses.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SOApi {

    @GET("2.2/search")
    fun getResultsFor(@Query("order") order: String = "desc",
                      @Query("sort") sort: String = "activity",
                      @Query("site") site: String = "stackoverflow",
                      @Query("filter") filter: String = "!9_bDDxJY5",
                      @Query("intitle") query: String) : Call<SearchResponse>

}