package com.example.image_drawer.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.api.main.responses.ImageDto
import com.example.image_drawer.databinding.ItemLessonViewBinding


class LessonImageVM(
    data : ImageDto
) : ItemVM {
    override val type = 1

    val url = data.image


}

private fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<LessonImageVM> {
    val layoutInflater = LayoutInflater.from(parent.context)
    val binding = ItemLessonViewBinding.inflate(layoutInflater, parent, false)
    return ViewHolder(binding)
}

class ViewPagerAdapter : CommonAdapter<LessonImageVM>(::onCreateViewHolder)