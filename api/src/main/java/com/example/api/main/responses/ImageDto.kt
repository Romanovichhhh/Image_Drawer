package com.example.api.main.responses

import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("image")
    val image: String,
    @SerializedName("image_min")
    val imageMin: String,
)
