package com.natiqhaciyef.witapplication.data.di.module

import com.natiqhaciyef.witapplication.data.local.ContactDao
import com.natiqhaciyef.witapplication.data.network.NetworkConfig
import com.natiqhaciyef.witapplication.data.network.service.ContactService
import com.natiqhaciyef.witapplication.data.network.service.InterviewQuestionService
import com.natiqhaciyef.witapplication.data.network.service.PostService
import com.natiqhaciyef.witapplication.data.network.service.UserService
import com.natiqhaciyef.witapplication.data.source.ContactDataSource
import com.natiqhaciyef.witapplication.data.source.InterviewQuestionDataSource
import com.natiqhaciyef.witapplication.data.source.PostDataSource
import com.natiqhaciyef.witapplication.data.source.UserDataSource
import com.natiqhaciyef.witapplication.domain.repository.ContactRepository
import com.natiqhaciyef.witapplication.domain.repository.InterviewQuestionRepository
import com.natiqhaciyef.witapplication.domain.repository.PostRepository
import com.natiqhaciyef.witapplication.domain.repository.UserRepository
import com.natiqhaciyef.witapplication.domain.repository.impl.ContactRepositoryImpl
import com.natiqhaciyef.witapplication.domain.repository.impl.InterviewQuestionRepositoryImpl
import com.natiqhaciyef.witapplication.domain.repository.impl.PostRepositoryImpl
import com.natiqhaciyef.witapplication.domain.repository.impl.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(NetworkConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(NetworkConfig.logger.build())
        .build()


    // provide services
    @Provides
    @Singleton
    fun provideUserService(network: Retrofit): UserService = network.create(UserService::class.java)

    @Provides
    @Singleton
    fun providePostService(network: Retrofit): PostService = network.create(PostService::class.java)

    @Provides
    @Singleton
    fun provideContactService(network: Retrofit): ContactService =
        network.create(ContactService::class.java)

    @Provides
    @Singleton
    fun provideInterviewQuestionService(network: Retrofit): InterviewQuestionService =
        network.create(InterviewQuestionService::class.java)


    // provide data sources
    @Provides
    @Singleton
    fun provideUserDataSource(service: UserService) = UserDataSource(service)

    @Provides
    @Singleton
    fun providePostDataSource(service: PostService) = PostDataSource(service)

    @Provides
    @Singleton
    fun provideContactDataSource(service: ContactService, dao: ContactDao) =
        ContactDataSource(service, dao)

    @Provides
    @Singleton
    fun provideInterviewQuestionDataSource(service: InterviewQuestionService) =
        InterviewQuestionDataSource(service)


    // provide data sources
    @Provides
    @Singleton
    fun provideUserRepository(ds: UserDataSource) = UserRepositoryImpl(ds) as UserRepository

    @Provides
    @Singleton
    fun providePostRepository(ds: PostDataSource) = PostRepositoryImpl(ds) as PostRepository

    @Provides
    @Singleton
    fun provideContactRepository(ds: ContactDataSource) =
        ContactRepositoryImpl(ds) as ContactRepository

    @Provides
    @Singleton
    fun provideInterviewQuestionRepository(ds: InterviewQuestionDataSource) =
        InterviewQuestionRepositoryImpl(ds) as InterviewQuestionRepository
}