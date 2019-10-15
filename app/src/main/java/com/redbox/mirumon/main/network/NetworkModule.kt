package com.redbox.mirumon.main.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    val retrofit: Retrofit
        get() {
            val interceptorLogger = HttpLoggingInterceptor()
            interceptorLogger.level = (HttpLoggingInterceptor.Level.BASIC)
            val clientLogger = OkHttpClient.Builder().addInterceptor(interceptorLogger).build()

            return Retrofit.Builder()
                .client(clientLogger)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.mirumon.dev/")
                .build()
        }
}