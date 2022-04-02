package com.example.api.main.responses

import com.google.gson.annotations.SerializedName

data class ImagesListDto(
    @SerializedName("title")
    val title: String,
    @SerializedName("images")
    val images: List<String>
)
