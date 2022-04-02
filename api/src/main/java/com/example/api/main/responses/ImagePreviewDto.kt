package com.example.api.main.responses

import com.google.gson.annotations.SerializedName

data class ImagePreviewDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("title")
    val title: String,
)