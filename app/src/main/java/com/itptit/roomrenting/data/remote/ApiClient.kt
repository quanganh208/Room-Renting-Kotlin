package com.itptit.roomrenting.data.remote

import android.content.Context
import com.itptit.roomrenting.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


object ApiClient {
    private lateinit var appContext: Context

    fun init(context: Context) {
        appContext = context.applicationContext
    }

    private fun getAccessToken(): String? {
        val sharedPreferences = appContext.getSharedPreferences(Constants.LOGIN_PREFS, Context.MODE_PRIVATE)
        return sharedPreferences.getString(Constants.ACCESS_TOKEN, null)
    }

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            getAccessToken()?.let {
                requestBuilder.addHeader("Authorization", "Bearer $it")
            }
            chain.proceed(requestBuilder.build())
        }
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val authService: AuthService = retrofit.create(AuthService::class.java)
    val houseService: HouseService = retrofit.create(HouseService::class.java)
}
