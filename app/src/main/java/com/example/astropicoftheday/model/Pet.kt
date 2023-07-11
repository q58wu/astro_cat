package com.example.astropicoftheday.model

import com.google.gson.annotations.SerializedName

data class Pet(
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    val height: Int,
)
