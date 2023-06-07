package com.example.astropicoftheday.model

import com.google.gson.annotations.SerializedName
import java.util.Date

open class BaseCard(
    @SerializedName("copyright")
    val copyRight: String? = null,
    @SerializedName("date")
    val date: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("explanation")
    val body: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("hdurl")
    val hdurl: String? = null,
    @SerializedName("media_type")
    val mediaType: MediaType
) {
    class ImageCard(copyRight: String, date: String, title: String, body: String, url: String, hdurl: String) :
        BaseCard(copyRight, date, title, body, url, hdurl, MediaType.IMAGE)

    class VideoCard(copyRight: String, date: String, title: String, body: String, url: String, hdurl: String) :
        BaseCard(copyRight, date, title, body, url, hdurl, MediaType.VIDEO)
}


enum class MediaType{
    @SerializedName("video")
    VIDEO,
    @SerializedName("image")
    IMAGE
}