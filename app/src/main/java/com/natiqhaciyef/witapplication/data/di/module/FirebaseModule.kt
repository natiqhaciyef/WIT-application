package com.natiqhaciyef.witapplication.data.di.module

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.witapplication.data.source.FirebaseDataSource
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
    fun provideFirestore() = Firebase.firestore

    @Provides
    @Singleton
    fun provideFirebaseDataSource(auth: FirebaseAuth, firestore: FirebaseFirestore) =
        FirebaseDataSource(auth, firestore)

    @Provides
    @Singleton
    fun provideFirebaseRepository(ds: FirebaseDataSource) =
        FirebaseRepositoryImpl(ds)

}