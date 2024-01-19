package com.natiqhaciyef.domain.domain.usecase.remote.exam

import com.natiqhaciyef.domain.domain.base.BaseUseCase
import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.domain.domain.repository.ExamRepository
import com.natiqhaciyef.domain.domain.base.ConfigUseCase
import com.natiqhaciyef.util.common.mappers.toMappedExam
import com.natiqhaciyef.util.models.mapped.MappedExamModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllExamsUseCase @Inject constructor(
    repository: ExamRepository,
) : BaseUseCase<ExamRepository>(repository) {

    suspend operator fun invoke() = flow {
        emit(Resource.loading(null))

        if (repository.getAllExams().examResult != null) {
            val questions = mutableListOf<MappedExamModel>()
            for (que in repository.getAllExams().examResult!!) {
                que.toMappedExam()?.let { questions.add(it) }
            }

            emit(Resource.success(questions))
        } else {
            emit(Resource.error(ConfigUseCase.LOADING_FAIL, null))
        }
    }

}