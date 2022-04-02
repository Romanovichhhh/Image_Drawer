package com.example.api.core

import retrofit2.Retrofit

interface BaseService

internal inline fun <reified T : BaseService> Retrofit.make(): T = create(T::class.java)