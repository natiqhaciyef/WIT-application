package com.natiqhaciyef.domain.domain.usecase.local.interview_question

import com.natiqhaciyef.domain.domain.base.BaseUseCase
import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.util.models.InterviewQuestionModel
import com.natiqhaciyef.domain.domain.repository.InterviewQuestionRepository
import com.natiqhaciyef.domain.domain.base.ConfigUseCase
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoveSavedInterviewQuestionUseCase @Inject constructor(
    interviewQuestionRepository: InterviewQuestionRepository
) : BaseUseCase<InterviewQuestionRepository>(interviewQuestionRepository) {

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
            emit(Resource.success(ConfigUseCase.REMOVE_SUCCESS))
        } else {
            emit(Resource.error(ErrorMessages.EMPTY_FIELD, null))
        }
    }

}