package com.natiqhaciyef.witapplication.domain.usecase.local.interview_question

import com.natiqhaciyef.voyagersaz.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.witapplication.common.Resource
import com.natiqhaciyef.witapplication.data.models.InterviewQuestionModel
import com.natiqhaciyef.witapplication.domain.repository.InterviewQuestionRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoveSavedInterviewQuestionUseCase @Inject constructor(
    private val repository: InterviewQuestionRepository,
) {

    suspend operator fun invoke(questionModel: InterviewQuestionModel) = flow {
        emit(Resource.loading(null))

        repository.removeSavedInterviewQuestion(questionModel)
        emit(Resource.success(BaseUseCase.REMOVE_SUCCESS))
    }

}