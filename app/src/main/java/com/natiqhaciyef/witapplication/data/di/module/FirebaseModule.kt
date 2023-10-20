package com.natiqhaciyef.witapplication.data.di.module

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.witapplication.data.source.FirebaseDataSource
import com.natiqhaciyef.witapplication.domain.repository.FirebaseRepository
import com.natiqhaciyef.witapplication.domain.repository.impl.FirebaseRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideAuth() = Firebase.auth

    @Provides
    @Singleton
    fun provideFirebaseDataSource(auth: FirebaseAuth) = FirebaseDataSource(auth)

    @Provides
    @Singleton
    fun provideFirebaseRepository(ds: FirebaseDataSource) = FirebaseRepositoryImpl(ds) as FirebaseRepository

}