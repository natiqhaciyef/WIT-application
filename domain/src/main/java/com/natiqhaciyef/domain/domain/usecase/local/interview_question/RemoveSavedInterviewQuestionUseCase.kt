package com.natiqhaciyef.domain.domain.usecase.local.interview_question

import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.util.models.InterviewQuestionModel
import com.natiqhaciyef.domain.domain.repository.InterviewQuestionRepository
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoveSavedInterviewQuestionUseCase @Inject constructor(
    private val repository: InterviewQuestionRepository,
) {

    suspend operator fun invoke(questionModel: InterviewQuestionModel) = flow {
        emit(Resource.loading(null))

        if (questionModel.field.isNotEmpty() &&
            questionModel.level.isNotEmpty() &&
            questionModel.date.isNotEmpty() &&
            questionModel.description.isNotEmpty() &&
            questionModel.origin.isNotEmpty() &&
            questionModel.description.isNotEmpty() &&
            questionModel.solution.isNotEmpty() &&
            questionModel.field.isNotEmpty() &&
            questionModel.subfield.isNotEmpty() &&
            questionModel.title.isNotEmpty()
        ) {
            repository.removeSavedInterviewQuestion(questionModel)
            emit(Resource.success(BaseUseCase.REMOVE_SUCCESS))
        } else {
            emit(Resource.error(ErrorMessages.EMPTY_FIELD, null))
        }
    }

}