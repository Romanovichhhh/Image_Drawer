package com.example.image_drawer.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.api.main.responses.ImagePreviewDto
import com.example.image_drawer.databinding.ItemLessonsViewBinding

class SingleLessonItemVM(
    data : ImagePreviewDto,
    val onLessonClick : (vm: SingleLessonItemVM) -> Unit
) : ItemVM {
    override val type = 1

    val id = data.id

    val url = data.image

    val title = data.title

}

private fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<SingleLessonItemVM> {
    val layoutInflater = LayoutInflater.from(parent.context)
    val binding = ItemLessonsViewBinding.inflate(layoutInflater, parent, false)
    return ViewHolder(binding)
}

class LessonsAdapter : CommonAdapter<SingleLessonItemVM>(::onCreateViewHolder)