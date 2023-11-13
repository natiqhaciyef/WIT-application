package com.natiqhaciyef.domain.domain.usecase.remote.exam

import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.domain.domain.repository.ExamRepository
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.util.common.mappers.toMappedExam
import com.natiqhaciyef.util.models.mapped.MappedExamModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllExamsUseCase @Inject constructor(
    private val repository: ExamRepository,
) {

    suspend operator fun invoke() = flow {
        emit(Resource.loading(null))

        if (repository.getAllExams().examResult != null) {
            val questions = mutableListOf<MappedExamModel>()
            for (que in repository.getAllExams().examResult!!){
                questions.add(que.toMappedExam())
            }

            emit(Resource.success(questions))
        } else {
            emit(Resource.error(BaseUseCase.LOADING_FAIL, null))
        }
    }

}