package com.itptit.roomrenting.data.remote

import com.itptit.roomrenting.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiProvincesClient {
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            chain.proceed(requestBuilder.build())
        }
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.URL_PROVINCES)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val provincesService: VietnamProvincesService =
        retrofit.create(VietnamProvincesService::class.java)
}