package com.example.image_drawer


import com.example.api.core.Api
import com.example.image_drawer.utils.BaseVM
import com.example.image_drawer.utils.LessonImageVM
import com.example.image_drawer.utils.ViewPagerAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ResultFragmentVM(
    val id: String
) : BaseVM() {

    val adapter = ViewPagerAdapter()


    fun loadLesson() {
        Api.main.loadImagesById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ imageList ->
                imageList.map {
                    LessonImageVM(it)
                }
            }, {
                it.printStackTrace()
            }).disposeOnCleared()
    }
}