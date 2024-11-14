package com.itptit.roomrenting.di

import android.app.Application
import com.itptit.roomrenting.data.manger.LocalUserMangerImpl
import com.itptit.roomrenting.data.remote.AuthApi
import com.itptit.roomrenting.data.repository.AuthRepositoryImpl
import com.itptit.roomrenting.domain.manger.LocalUserManger
import com.itptit.roomrenting.domain.repository.AuthRepository
import com.itptit.roomrenting.domain.usecases.app_entry.AppEntryUseCases
import com.itptit.roomrenting.domain.usecases.app_entry.ReadAppEntry
import com.itptit.roomrenting.domain.usecases.app_entry.SaveAppEntry
import com.itptit.roomrenting.domain.usecases.auth.AuthUseCases
import com.itptit.roomrenting.domain.usecases.auth.Login
import com.itptit.roomrenting.domain.usecases.auth.Register
import com.itptit.roomrenting.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application
    ): LocalUserManger = LocalUserMangerImpl(context = application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManger
    ): AppEntryUseCases = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )

    @Provides
    @Singleton
    fun provideApiInstance(): AuthApi {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(authApi: AuthApi): AuthRepository {
        return AuthRepositoryImpl(authApi)
    }

    @Provides
    @Singleton
    fun provideAuthUseCases(
        authRepository: AuthRepository
    ): AuthUseCases {
        return AuthUseCases(
            login = Login(authRepository),
            register = Register(authRepository)
        )
    }

}