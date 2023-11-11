package com.natiqhaciyef.domain.domain.usecase.local.interview_question

import com.natiqhaciyef.data.common.Resource
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.util.models.InterviewQuestionModel
import com.natiqhaciyef.domain.domain.repository.InterviewQuestionRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveInterviewQuestionUseCase @Inject constructor(
    private val repository: InterviewQuestionRepository,
) {

    suspend operator fun invoke(questionModel: InterviewQuestionModel) = flow {
        emit(Resource.loading(null))

        repository.saveInterviewQuestion(questionModel)
        emit(Resource.success(BaseUseCase.INSERT_SUCCESS))
    }
}