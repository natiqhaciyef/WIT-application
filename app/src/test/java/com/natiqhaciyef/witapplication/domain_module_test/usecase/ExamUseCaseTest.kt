package com.natiqhaciyef.witapplication.domain_module_test.usecase

import com.google.common.truth.Truth.assertThat
import com.natiqhaciyef.domain.domain.base.ConfigUseCase
import com.natiqhaciyef.domain.domain.usecase.local.exam.ParticipateExamUseCase
import com.natiqhaciyef.domain.domain.usecase.local.exam.RemoveExamParticipationUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.exam.GetAllExamsUseCase
import com.natiqhaciyef.domain.domain.usecase.remote.exam.InsertExamUseCase
import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.util.common.mappers.toExam
import com.natiqhaciyef.util.common.util.objects.DefaultImpl
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.util.models.mapped.MappedExamModel
import com.natiqhaciyef.witapplication.domain_module_test.repository.FakeExamRepository
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ExamUseCaseTest {

    private val fakeExamRepository =
        FakeExamRepository(mutableListOf(DefaultImpl.mappedExamModel.toExam()!!))

    @Test
    fun `get all exams use case (remote) returns success`() =
        runTest {
            val result = GetAllExamsUseCase(fakeExamRepository).invoke().last()

            assertThat(result).isEqualTo(Resource.success(mutableListOf(DefaultImpl.mappedExamModel)))
        }

    @Test
    fun `insert exam use case (remote) returns success`() =
        runTest {
            val examModel = MappedExamModel(
                id = 0,
                title = "Title 1",
                field = "field 1",
                image = "image 1",
                questions = listOf(DefaultImpl.questionModel),
                level = "Level 1",
                limitPoint = 40.0,
                isActive = true
            )
            val result = InsertExamUseCase(fakeExamRepository).invoke(examModel).last()

            assertThat(result).isEqualTo(Resource.success(ConfigUseCase.INSERT_SUCCESS))
        }

    @Test
    fun `remove participated exam use case (local) returns success`() =
        runTest {
            val examModel = MappedExamModel(
                id = 0,
                title = "Title 1",
                field = "field 1",
                image = "image 1",
                questions = listOf(DefaultImpl.questionModel),
                level = "Level 1",
                limitPoint = 40.0,
                isActive = true
            )
            val result = RemoveExamParticipationUseCase(fakeExamRepository).invoke(examModel).last()

            assertThat(result).isEqualTo(Resource.success(ConfigUseCase.REMOVE_SUCCESS))
        }

    @Test
    fun `save or participate exam use case (local) returns success`() =
        runTest {
            val examModel = MappedExamModel(
                id = 0,
                title = "Title 1",
                field = "field 1",
                image = "image 1",
                questions = listOf(DefaultImpl.questionModel),
                level = "Level 1",
                limitPoint = 40.0,
                isActive = true
            )
            val result = ParticipateExamUseCase(fakeExamRepository).invoke(examModel).last()

            assertThat(result).isEqualTo(Resource.success(ConfigUseCase.INSERT_SUCCESS))
        }

    @Test
    fun `remove participated exam use case (local) with exam model contains empty field returns error`() =
        runTest {
            val examModel = MappedExamModel(
                id = 0,
                title = "",
                field = "field 1",
                image = "image 1",
                questions = listOf(DefaultImpl.questionModel),
                level = "Level 1",
                limitPoint = 40.0,
                isActive = true
            )
            val result = RemoveExamParticipationUseCase(fakeExamRepository).invoke(examModel).last()

            assertThat(result).isEqualTo(Resource.error(ErrorMessages.EMPTY_FIELD, null))
        }

    @Test
    fun `insert exam use case (remote) with exam model contains empty field returns error`() =
        runTest {
            val examModel = MappedExamModel(
                id = 0,
                title = "",
                field = "field 1",
                image = "image 1",
                questions = listOf(DefaultImpl.questionModel),
                level = "Level 1",
                limitPoint = 40.0,
                isActive = true
            )
            val result = InsertExamUseCase(fakeExamRepository).invoke(examModel).last()

            assertThat(result).isEqualTo(Resource.error(ErrorMessages.EMPTY_FIELD, null))
        }

    @Test
    fun `save or participate exam use case (local) with exam model contains empty field returns error`() =
        runTest {
            val examModel = MappedExamModel(
                id = 0,
                title = "",
                field = "field 1",
                image = "image 1",
                questions = listOf(DefaultImpl.questionModel),
                level = "Level 1",
                limitPoint = 40.0,
                isActive = true
            )
            val result = ParticipateExamUseCase(fakeExamRepository).invoke(examModel).last()

            assertThat(result).isEqualTo(Resource.error(ErrorMessages.EMPTY_FIELD, null))
        }
}