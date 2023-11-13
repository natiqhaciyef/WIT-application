package com.natiqhaciyef.domain.domain.usecase.local.interview_question

import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.domain.domain.repository.InterviewQuestionRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllSavedInterviewQuestionsUseCase @Inject constructor(
    private val interviewQuestionRepository: InterviewQuestionRepository,
) {

    suspend operator fun invoke() = flow {
        emit(Resource.loading(null))

        if (interviewQuestionRepository.getAllSavedInterviewQuestions() != null) {
            emit(Resource.success(interviewQuestionRepository.getAllSavedInterviewQuestions()))
        } else {
            emit(Resource.error(BaseUseCase.LOADING_FAIL, null))
        }
    }
}