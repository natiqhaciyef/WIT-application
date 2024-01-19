package com.natiqhaciyef.domain.domain.usecase.local.interview_question

import com.natiqhaciyef.domain.domain.base.BaseUseCase
import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.domain.domain.repository.InterviewQuestionRepository
import com.natiqhaciyef.domain.domain.base.ConfigUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllSavedInterviewQuestionsUseCase @Inject constructor(
    interviewQuestionRepository: InterviewQuestionRepository
) : BaseUseCase<InterviewQuestionRepository>(interviewQuestionRepository) {

    suspend operator fun invoke() = flow {
        emit(Resource.loading(null))

        if (repository.getAllSavedInterviewQuestions() != null) {
            emit(Resource.success(repository.getAllSavedInterviewQuestions()))
        } else {
            emit(Resource.error(ConfigUseCase.LOADING_FAIL, null))
        }
    }
}