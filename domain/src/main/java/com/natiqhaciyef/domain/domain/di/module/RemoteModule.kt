package com.natiqhaciyef.domain.domain.di.module

import com.natiqhaciyef.data.local.dao.ContactDao
import com.natiqhaciyef.data.local.dao.ExamDao
import com.natiqhaciyef.data.local.dao.InterviewQuestionDao
import com.natiqhaciyef.data.local.dao.PostDao
import com.natiqhaciyef.data.network.NetworkConfig
import com.natiqhaciyef.data.network.service.ContactService
import com.natiqhaciyef.data.network.service.ExamService
import com.natiqhaciyef.data.network.service.InterviewQuestionService
import com.natiqhaciyef.data.network.service.PostService
import com.natiqhaciyef.data.source.ContactDataSource
import com.natiqhaciyef.data.source.ExamDataSource
import com.natiqhaciyef.data.source.InterviewQuestionDataSource
import com.natiqhaciyef.data.source.PostDataSource
import com.natiqhaciyef.domain.domain.repository.ContactRepository
import com.natiqhaciyef.domain.domain.repository.ExamRepository
import com.natiqhaciyef.domain.domain.repository.InterviewQuestionRepository
import com.natiqhaciyef.domain.domain.repository.PostRepository
import com.natiqhaciyef.domain.domain.repository.impl.ContactRepositoryImpl
import com.natiqhaciyef.domain.domain.repository.impl.ExamRepositoryImpl
import com.natiqhaciyef.domain.domain.repository.impl.InterviewQuestionRepositoryImpl
import com.natiqhaciyef.domain.domain.repository.impl.PostRepositoryImpl
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
    fun providePostService(network: Retrofit): PostService = network.create(PostService::class.java)

    @Provides
    @Singleton
    fun provideContactService(network: Retrofit): ContactService =
        network.create(ContactService::class.java)

    @Provides
    @Singleton
    fun provideInterviewQuestionService(network: Retrofit): InterviewQuestionService =
        network.create(InterviewQuestionService::class.java)

    @Provides
    @Singleton
    fun provideExamService(network: Retrofit): ExamService =
        network.create(ExamService::class.java)


    // provide data sources
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

    @Provides
    @Singleton
    fun provideExamDataSource(service: ExamService, dao: ExamDao) = ExamDataSource(service, dao)


    // provide data sources
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

    @Provides
    @Singleton
    fun provideExamRepository(ds: ExamDataSource) =
        ExamRepositoryImpl(ds) as ExamRepository
}