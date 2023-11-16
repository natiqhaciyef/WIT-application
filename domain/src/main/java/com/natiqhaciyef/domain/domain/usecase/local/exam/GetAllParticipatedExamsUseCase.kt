package com.natiqhaciyef.domain.domain.usecase.local.exam

import com.natiqhaciyef.domain.domain.repository.ExamRepository
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.util.common.mappers.toMappedExam
import com.natiqhaciyef.util.models.mapped.MappedExamModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllParticipatedExamsUseCase @Inject constructor(
    private val repository: ExamRepository,
) {

    suspend operator fun invoke() = flow {
        emit(Resource.loading(null))

        val result = repository.getAllParticipatedExams()
        if (result != null) {
            val mapped = mutableListOf<MappedExamModel>()
            for (exam in result){
                exam.toMappedExam()?.let { mapped.add(it) }
            }
            emit(Resource.success(mapped))
        } else {
            emit(Resource.error(BaseUseCase.LOADING_FAIL, null))
        }
    }
}