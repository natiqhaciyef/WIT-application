package com.natiqhaciyef.witapplication.domain.usecase.remote.interview_question

import com.natiqhaciyef.witapplication.common.Resource
import com.natiqhaciyef.witapplication.data.models.InterviewQuestionModel
import com.natiqhaciyef.witapplication.domain.repository.InterviewQuestionRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertInterviewQuestionUseCase @Inject constructor(
    private val repository: InterviewQuestionRepository,
) {

    suspend operator fun invoke(questionModel: InterviewQuestionModel) = flow {
        emit(Resource.loading(null))

        val result = repository.insertInterviewQuestion(questionModel)

        if (result.success > 0) {
            emit(Resource.success(result.message))
        } else {
            emit(Resource.error(result.message, null))
        }
    }
}