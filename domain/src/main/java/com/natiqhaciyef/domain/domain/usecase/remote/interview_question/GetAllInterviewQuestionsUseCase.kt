package com.natiqhaciyef.domain.domain.usecase.remote.interview_question

import com.natiqhaciyef.domain.domain.base.BaseUseCase
import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.domain.domain.repository.InterviewQuestionRepository
import com.natiqhaciyef.domain.domain.base.ConfigUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllInterviewQuestionsUseCase @Inject constructor(
    interviewQuestionRepository: InterviewQuestionRepository
) : BaseUseCase<InterviewQuestionRepository>(interviewQuestionRepository) {

    suspend operator fun invoke() = flow {
        emit(Resource.loading(null))

        val result = repository.getAllInterviewQuestion().list
        if (result != null) {
            emit(Resource.success(result))
        } else {
            emit(Resource.error(ConfigUseCase.LOADING_FAIL, null))
        }
    }

}