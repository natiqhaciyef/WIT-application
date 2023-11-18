package com.natiqhaciyef.domain.domain.di.module

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.natiqhaciyef.data.source.FirebaseDataSource
import com.natiqhaciyef.domain.domain.repository.FirebaseRepository
import com.natiqhaciyef.domain.domain.repository.impl.FirebaseRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

//    @Provides
//    @Singleton
//    fun provideAuth() = Firebase.auth
//
//    @Provides
//    @Singleton
//    fun provideFirestore() = Firebase.firestore
//
//    @Provides
//    @Singleton
//    fun provideStorage() = Firebase.storage

    @Provides
    @Singleton
    fun provideFirebaseDataSource() =
        FirebaseDataSource(FirebaseAuth.getInstance(), FirebaseFirestore.getInstance(), FirebaseStorage.getInstance())

    @Provides
    @Singleton
    fun provideFirebaseRepository(ds: FirebaseDataSource) =
        FirebaseRepositoryImpl(ds) as FirebaseRepository

}