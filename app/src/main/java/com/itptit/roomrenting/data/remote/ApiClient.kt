package com.itptit.roomrenting.data.remote

import android.content.Context
import com.google.gson.Gson
import com.itptit.roomrenting.data.remote.roomrenting.AssetService
import com.itptit.roomrenting.data.remote.roomrenting.AuthService
import com.itptit.roomrenting.data.remote.roomrenting.HouseService
import com.itptit.roomrenting.data.remote.roomrenting.InvoiceService
import com.itptit.roomrenting.data.remote.roomrenting.RentedRoomService
import com.itptit.roomrenting.data.remote.roomrenting.RoomService
import com.itptit.roomrenting.domain.model.auth.login.Data
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
        val sharedPreferences =
            appContext.getSharedPreferences(Constants.LOGIN_PREFS, Context.MODE_PRIVATE)
        val gson = Gson()
        val dataJson = sharedPreferences.getString(Constants.USER_DATA, null)
        return gson.fromJson(dataJson, Data::class.java)?.jwt
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
    val roomService: RoomService = retrofit.create(RoomService::class.java)
    val assetService: AssetService = retrofit.create(AssetService::class.java)
    val invoiceService: InvoiceService = retrofit.create(InvoiceService::class.java)
    val rentedRoomService: RentedRoomService = retrofit.create(RentedRoomService::class.java)
}
