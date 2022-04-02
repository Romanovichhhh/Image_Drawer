package com.example.image_drawer

import com.example.api.core.Api
import com.example.image_drawer.utils.BaseVM
import com.example.image_drawer.utils.LessonsAdapter
import com.example.image_drawer.utils.SingleLessonItemVM
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers

class EnterFragmentVM(
    val navigateToResult: (String) -> Unit
) : BaseVM() {

    val adapter = LessonsAdapter()

    fun loadLessonPreviews() {
        Api.main.loadImages()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ imageList ->
                val testList = imageList.toMutableList()
                testList.addAll(imageList)
                testList.addAll(imageList)
                testList.addAll(imageList)
                testList.addAll(imageList)
                testList.addAll(imageList)
                testList.addAll(imageList)
                testList.addAll(imageList)
                testList.addAll(imageList)
                testList.map {
                    SingleLessonItemVM(it) { vm ->
                        navigateToResult(vm.id)
                    }
                }.let {
                    adapter.setItems(it)
                }
            }, {
                it.printStackTrace()
            }).disposeOnCleared()
    }

}