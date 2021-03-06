package com.safiej.stackoverflowapplication.network.responses

import com.google.gson.annotations.SerializedName
import com.safiej.stackoverflowapplication.data.model.Question

class SearchResponse (
    @SerializedName("items")
    val resultList: ArrayList<Question>
)