package com.safiej.stackoverflowapplication.data.model

import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("profile_image")
    val avatarUrl : String,

    @SerializedName("display_name")
    val name: String
)