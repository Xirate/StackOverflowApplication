package com.safiej.stackoverflowapplication.data.model

import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("is_answered")
    val answered: Boolean,

    @SerializedName("answer_count")
    val answers: Int,

    @SerializedName("body")
    val body: String,

    @SerializedName("last_activity_date")
    val lastActivity: Long,

    @SerializedName("link")
    val url: String,

    @SerializedName("creation_date")
    val creationDate: Long,

    @SerializedName("title")
    val title: String,

    @SerializedName("tags")
    val tags: ArrayList<String>,

    @SerializedName("owner")
    val owner: Owner
)