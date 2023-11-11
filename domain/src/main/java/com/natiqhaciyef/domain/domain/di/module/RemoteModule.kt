package com.natiqhaciyef.domain.domain.di.module

import com.natiqhaciyef.data.local.dao.ContactDao
import com.natiqhaciyef.data.local.dao.InterviewQuestionDao
import com.natiqhaciyef.data.local.dao.PostDao
import com.natiqhaciyef.data.network.NetworkConfig
import com.natiqhaciyef.data.network.service.ContactService
import com.natiqhaciyef.data.network.service.InterviewQuestionService
import com.natiqhaciyef.data.network.service.PostService
import com.natiqhaciyef.data.network.service.UserService
import com.natiqhaciyef.data.network.service.VerifiedUserService
import com.natiqhaciyef.data.source.ContactDataSource
import com.natiqhaciyef.data.source.InterviewQuestionDataSource
import com.natiqhaciyef.data.source.PostDataSource
import com.natiqhaciyef.data.source.UserDataSource
import com.natiqhaciyef.data.source.VerifiedUserDataSource
import com.natiqhaciyef.domain.domain.repository.ContactRepository
import com.natiqhaciyef.domain.domain.repository.InterviewQuestionRepository
import com.natiqhaciyef.domain.domain.repository.PostRepository
import com.natiqhaciyef.domain.domain.repository.UserRepository
import com.natiqhaciyef.domain.domain.repository.VerifiedUserRepository
import com.natiqhaciyef.domain.domain.repository.impl.ContactRepositoryImpl
import com.natiqhaciyef.domain.domain.repository.impl.InterviewQuestionRepositoryImpl
import com.natiqhaciyef.domain.domain.repository.impl.PostRepositoryImpl
import com.natiqhaciyef.domain.domain.repository.impl.UserRepositoryImpl
import com.natiqhaciyef.domain.domain.repository.impl.VerifiedUserRepositoryImpl
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
    fun provideVerifiedUserService(network: Retrofit): VerifiedUserService =
        network.create(VerifiedUserService::class.java)

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
    fun provideVerifiedUserDataSource(service: VerifiedUserService) =
        VerifiedUserDataSource(service)

    @Provides
    @Singleton
    fun providePostDataSource(service: PostService, dao: PostDao) = PostDataSource(service, dao)

    @Provides
    @Singleton
    fun provideContactDataSource(service: ContactService, dao: ContactDao) =
        ContactDataSource(service, dao)

    @Provides
    @Singleton
    fun provideInterviewQuestionDataSource(
        service: InterviewQuestionService,
        dao: InterviewQuestionDao,
    ) = InterviewQuestionDataSource(service, dao)


    // provide data sources
    @Provides
    @Singleton
    fun provideUserRepository(ds: UserDataSource) = UserRepositoryImpl(ds) as UserRepository

    @Provides
    @Singleton
    fun provideVerifiedUserRepository(ds: VerifiedUserDataSource) =
        VerifiedUserRepositoryImpl(ds) as VerifiedUserRepository

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