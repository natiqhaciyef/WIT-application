package com.natiqhaciyef.domain.domain.usecase.remote.exam

import com.natiqhaciyef.domain.domain.repository.ExamRepository
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.util.common.mappers.toExam
import com.natiqhaciyef.util.models.ExamModel
import com.natiqhaciyef.util.models.mapped.MappedExamModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertExamUseCase @Inject constructor(
    private val repository: ExamRepository,
) {

    suspend operator fun invoke(mappedExamModel: MappedExamModel) = flow {
        emit(Resource.loading(null))

        val examModel = mappedExamModel.toExam()
        val result = examModel?.let { repository.insertExam(it) }

        println(result?.message)
        if (result != null && result.success > 0) {
            emit(Resource.success(result.message))
        } else {
            emit(Resource.error(BaseUseCase.INSERT_FAIL, null))
        }
    }

}