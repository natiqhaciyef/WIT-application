package com.natiqhaciyef.domain.domain.usecase.remote.exam

import com.natiqhaciyef.domain.domain.repository.ExamRepository
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.util.common.mappers.toExam
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.util.models.ExamModel
import com.natiqhaciyef.util.models.mapped.MappedExamModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertExamUseCase @Inject constructor(
    private val repository: ExamRepository,
) {

    suspend operator fun invoke(mappedExamModel: MappedExamModel) = flow {
        emit(Resource.loading(null))

        if (
            mappedExamModel.title.isNotEmpty() &&
            mappedExamModel.level.isNotEmpty() &&
            mappedExamModel.image.isNotEmpty() &&
            mappedExamModel.field.isNotEmpty() &&
            mappedExamModel.questions.isNotEmpty()
        ) {
            val examModel = mappedExamModel.toExam()
            val result = examModel?.let { repository.insertExam(it) }

            if (result != null && result.success > 0) {
                emit(Resource.success(result.message))
            } else {
                emit(Resource.error(BaseUseCase.INSERT_FAIL, null))
            }
        } else {
            emit(Resource.error(ErrorMessages.EMPTY_FIELD, null))
        }
    }

}