package com.itptit.roomrenting.di

import android.app.Application
import com.itptit.roomrenting.data.manger.LocalUserMangerImpl
import com.itptit.roomrenting.domain.manger.LocalUserManger
import com.itptit.roomrenting.domain.usecases.app_entry.AppEntryUseCases
import com.itptit.roomrenting.domain.usecases.app_entry.ReadAppEntry
import com.itptit.roomrenting.domain.usecases.app_entry.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
}