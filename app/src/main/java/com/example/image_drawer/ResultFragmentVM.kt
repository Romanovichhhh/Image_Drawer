package com.example.image_drawer

import androidx.lifecycle.MutableLiveData
import com.example.api.core.Api
import com.example.image_drawer.utils.BaseVM
import com.example.image_drawer.utils.LessonImageVM
import com.example.image_drawer.utils.ViewPagerAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ResultFragmentVM(
    private val id: String
) : BaseVM() {

    val adapter = ViewPagerAdapter()
    val title = MutableLiveData<String>()
    val seekBarMax = MutableLiveData<Int>()

    fun loadLesson() {
        Api.main.loadImagesById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ imageList ->
                title.postValue(imageList.title)
                seekBarMax.postValue(imageList.images.size)
                imageList.images.map {
                    LessonImageVM(it)
                }.let {
                    adapter.setItems(it)
                }
            }, {
                it.printStackTrace()
            }).disposeOnCleared()
    }
}