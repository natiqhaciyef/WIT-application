package com.natiqhaciyef.domain.domain.usecase.local.exam

import com.natiqhaciyef.domain.domain.repository.ExamRepository
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.util.common.mappers.toExam
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.util.models.mapped.MappedExamModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoveExamParticipationUseCase @Inject constructor(
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
            examModel?.let { repository.removeParticipation(it) }
            emit(Resource.success(BaseUseCase.REMOVE_SUCCESS))
        } else {
            emit(Resource.error(ErrorMessages.EMPTY_FIELD, null))
        }
    }
}