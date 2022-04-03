package com.example.image_drawer.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.api.core.Api

fun convertMediaUrl(url: String): String {
    return if (url.startsWith("http")) {
        url
    } else {
        Api.mediaUrlString(url)
    }
}

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view.context)
        .load(convertMediaUrl(url))
        .into(view)
}