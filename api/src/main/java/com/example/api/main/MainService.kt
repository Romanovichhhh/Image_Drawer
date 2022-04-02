package com.example.api.main

import com.example.api.core.BaseService
import com.example.api.main.responses.ImageDto
import com.example.api.main.responses.ImagePreviewDto
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.*

interface MainService : BaseService {

    @Multipart
    @POST("upload_image/")
    fun uploadImage(@Part file: MultipartBody.Part): Single<String>

    // use me
    /*
    *         addDisposable(
            Api.chats.uploadImage(
                MultipartBody.Part.createFormData(
                    "image",
                    attachment.file.name,
                    UploadImageRequest(attachment.file, "image/jpeg")
                )
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .handleAllHTTPExceptions()
                .subscribe({
                    attachment.url = it
                }, { })
        )

    * */

    @GET("images/preview")
    fun loadImages(): Single<List<ImagePreviewDto>>

    @GET("images/{id}/")
    fun loadImagesById(
        @Path("id") id: String
    ): Single<List<ImageDto>>
}