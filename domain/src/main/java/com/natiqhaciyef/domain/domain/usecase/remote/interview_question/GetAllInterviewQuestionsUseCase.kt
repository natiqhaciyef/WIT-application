package com.natiqhaciyef.domain.domain.usecase.remote.interview_question

import com.natiqhaciyef.data.common.Resource
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.domain.domain.repository.InterviewQuestionRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllInterviewQuestionsUseCase @Inject constructor(
    private val interviewQuestionRepository: InterviewQuestionRepository
) {

    suspend operator fun invoke() = flow {
        emit(Resource.loading(null))

        val result = interviewQuestionRepository.getAllInterviewQuestion().list
        if (result != null) {
            emit(Resource.success(result))
        } else {
            emit(Resource.error(BaseUseCase.LOADING_FAIL, null))
        }
    }

}