package com.safiej.stackoverflowapplication.data.repository

import com.safiej.stackoverflowapplication.data.model.Question
import com.safiej.stackoverflowapplication.network.Rest
import com.safiej.stackoverflowapplication.network.responses.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object QuestionRepository {

    fun getQuestionsContaining(query: String, callback: (ArrayList<Question>) -> Unit) {
        Rest.soApi.getResultsFor(query = query).enqueue(object : Callback<SearchResponse> {
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                callback(ArrayList())
            }

            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                callback(response.body()?.resultList ?: ArrayList())
            }
        })
    }

}