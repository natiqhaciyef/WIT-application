package com.natiqhaciyef.witapplication.domain_module_test.usecase

import com.google.common.truth.Truth.assertThat
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.domain.domain.usecase.local.interview_question.GetAllSavedInterviewQuestionsUseCase
import com.natiqhaciyef.domain.domain.usecase.local.interview_question.GetAllSavedInterviewQuestionsUseCase_Factory
import com.natiqhaciyef.domain.domain.usecase.local.interview_question.RemoveSavedInterviewQuestionUseCase
import com.natiqhaciyef.domain.domain.usecase.local.interview_question.SaveInterviewQuestionUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.interview_question.GetAllInterviewQuestionsUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.interview_question.InsertInterviewQuestionUseCase
import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.util.common.util.objects.DefaultImpl
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.util.models.InterviewQuestionModel
import com.natiqhaciyef.witapplication.domain_module_test.repository.FakeInterviewQuestionRepository
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Test

class InterviewQuestionsUseCaseTest {
    private val fakeInterviewQuestionRepository =
        FakeInterviewQuestionRepository(mutableListOf(DefaultImpl.interviewQuestionModel))

    @Test
    fun `get all interview questions use case (remote) returns success`() = runTest {
        val result =
            GetAllInterviewQuestionsUseCase(fakeInterviewQuestionRepository).invoke().last()
        assertThat(result).isEqualTo(Resource.success(mutableListOf(DefaultImpl.interviewQuestionModel)))
    }

    @Test
    fun `insert interview question use case (remote) returns success`() = runTest {
        val interviewQuestionModel = DefaultImpl.interviewQuestionModel.copy(id = 1)
        val result = InsertInterviewQuestionUseCase(fakeInterviewQuestionRepository)
            .invoke(interviewQuestionModel)
            .last()
        assertThat(result).isEqualTo(Resource.success(BaseUseCase.INSERT_SUCCESS))
    }

    @Test
    fun `get all saved interview question use case (local) returns success`() = runTest {
        val result = GetAllSavedInterviewQuestionsUseCase(fakeInterviewQuestionRepository)
            .invoke()
            .last()
        assertThat(result).isEqualTo(Resource.success(listOf(DefaultImpl.interviewQuestionModel)))
    }

    @Test
    fun `save interview question use case (local) returns success`() = runTest {
        val interviewQuestionModel = DefaultImpl.interviewQuestionModel.copy(id = 0)
        val result = SaveInterviewQuestionUseCase(fakeInterviewQuestionRepository)
            .invoke(interviewQuestionModel)
            .last()
        assertThat(result).isEqualTo(Resource.success(BaseUseCase.INSERT_SUCCESS))
    }

    @Test
    fun `remove saved interview question use case (local) returns success`() = runTest {
        val interviewQuestionModel = DefaultImpl.interviewQuestionModel.copy(id = 0)
        val result = RemoveSavedInterviewQuestionUseCase(fakeInterviewQuestionRepository)
            .invoke(interviewQuestionModel)
            .last()
        assertThat(result).isEqualTo(Resource.success(BaseUseCase.REMOVE_SUCCESS))
    }

    @Test
    fun `save interview question use case (local) with questions model contains empty field returns error`() =
        runTest {
            val interviewQuestionModel = DefaultImpl.interviewQuestionModel.copy(id = 0, title = "")
            val result = SaveInterviewQuestionUseCase(fakeInterviewQuestionRepository)
                .invoke(interviewQuestionModel)
                .last()
            assertThat(result).isEqualTo(Resource.error(ErrorMessages.EMPTY_FIELD, null))
        }

    @Test
    fun `remove saved interview question use case (local) with questions model contains empty field returns error`() =
        runTest {
            val interviewQuestionModel = DefaultImpl.interviewQuestionModel.copy(id = 0, title = "")
            val result = RemoveSavedInterviewQuestionUseCase(fakeInterviewQuestionRepository)
                .invoke(interviewQuestionModel)
                .last()
            assertThat(result).isEqualTo(Resource.error(ErrorMessages.EMPTY_FIELD, null))
        }
}