package com.example.astropicoftheday.model

import com.google.gson.annotations.SerializedName

data class Joke(
    @SerializedName("setup")
    val setup: String,

    @SerializedName("delivery")
    val delivery: String,
)

data class JokesWrapper(
    @SerializedName("setup")
    val error: Boolean,

    @SerializedName("amount")
    val amount: Int,

    @SerializedName("jokes")
    val jokes: List<Joke>
)
