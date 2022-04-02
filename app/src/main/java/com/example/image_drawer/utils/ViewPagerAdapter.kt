package com.example.image_drawer.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.image_drawer.databinding.ItemLessonViewBinding

class LessonImageVM(
    data: String
) : ItemVM {
    override val type = 1
    val url = data
}

private fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<LessonImageVM> {
    val layoutInflater = LayoutInflater.from(parent.context)
    val binding = ItemLessonViewBinding.inflate(layoutInflater, parent, false)
    return ViewHolder(binding)
}

class ViewPagerAdapter : CommonAdapter<LessonImageVM>(::onCreateViewHolder)