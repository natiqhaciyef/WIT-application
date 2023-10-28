package com.natiqhaciyef.witapplication.data.di.module

import android.content.Context
import androidx.room.Room
import com.natiqhaciyef.witapplication.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideContactDao(db: AppDatabase) = db.getContactDao()

    @Provides
    @Singleton
    fun providePostDao(db: AppDatabase) = db.getPostDao()

}