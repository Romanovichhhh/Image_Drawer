package com.example.image_drawer

import com.example.api.core.Api
import com.example.api.main.requests.Attachment
import com.example.api.main.requests.UploadImageRequest
import com.example.image_drawer.utils.BaseVM
import com.example.image_drawer.utils.LessonsAdapter
import com.example.image_drawer.utils.SingleLessonItemVM
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import okhttp3.MultipartBody
class EnterFragmentVM(
    val navigateToResult: (String) -> Unit,
    val imageUploaded: () -> Unit
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

    fun uploadImage(attachment: Attachment) {
        Api.main.uploadImage(
            MultipartBody.Part.createFormData(
                "image",
                attachment.image.name,
                UploadImageRequest(attachment.image, "image/jpeg")
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
//                imageUploaded()
            }, {
                it.printStackTrace()
            }).disposeOnCleared()
        imageUploaded()
    }

}