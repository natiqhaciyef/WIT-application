package com.natiqhaciyef.witapplication.data.di.module

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
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
        FirebaseRepositoryImpl(ds)

}