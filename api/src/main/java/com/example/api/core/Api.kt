package com.example.api.core

import android.content.Context
import android.net.Uri
import com.example.api.R
import com.example.api.helper.HasStringValueConverterFactory
import com.example.api.helper.SingletonHolder
import com.example.api.main.MainService
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Api private constructor(context: Context) {

    init {
        val sharedPreferences = context.getSharedPreferences(
            context.getString(R.string.en_app_s),
            Context.MODE_PRIVATE
        )
        SessionManager.init(sharedPreferences)
    }

    private val retrofit: Retrofit by lazy { makeRetrofit() }

    private fun makeRetrofit(): Retrofit {
        val httpClient = okHttpClient()

        val gsonConverterFactory = GsonConverterFactory.create(
            GsonBuilder().create()
        )
        val rxCallFactory = RxJava2CallAdapterFactory
            .createWithScheduler(Schedulers.io())
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .client(httpClient)
            .addConverterFactory(gsonConverterFactory)
            .addConverterFactory(HasStringValueConverterFactory())
            .addCallAdapterFactory(rxCallFactory)
            .build()
    }

    companion object : SingletonHolder<Context, Api>(::Api) {
        private const val scheme = "http"
        private var serverHost = "62.113.102.194"
        private var port = 8000

        fun okHttpClient(): OkHttpClient {
            val builder = OkHttpClient.Builder()
            val httpClient = builder
                .addInterceptor(
                    HttpLoggingInterceptor().setLevel(
                        HttpLoggingInterceptor.Level.BODY
                    )
                )
                .build()
            return httpClient
        }

        @JvmStatic
        internal val baseURL by lazy {
            HttpUrl.Builder()
                .scheme(scheme)
                .host(serverHost)
                .port(port)
                .build()
        }

        val main: MainService by lazy { getInstanceOrFail().retrofit.make() }
    }
}