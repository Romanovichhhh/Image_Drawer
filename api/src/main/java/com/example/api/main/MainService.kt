package com.example.api.main

import com.example.api.core.BaseService
import com.example.api.main.responses.ImagesListDto
import com.example.api.main.responses.ImagePreviewDto
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.*

interface MainService : BaseService {

    @Multipart
    @POST("upload-image")
    fun uploadImage(
        @Part image: MultipartBody.Part
    ): Single<String>

    @GET("images/preview")
    fun loadImages(): Single<List<ImagePreviewDto>>

    @GET("images/{id}")
    fun loadImagesById(
        @Path("id") id: String
    ): Single<ImagesListDto>
}