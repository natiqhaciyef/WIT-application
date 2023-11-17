package com.natiqhaciyef.witapplication.domain_module_test.repository

import com.natiqhaciyef.domain.domain.repository.InterviewQuestionRepository
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.util.models.InterviewQuestionModel
import com.natiqhaciyef.util.models.result.CRUDResponse
import com.natiqhaciyef.util.models.result.ExamResult
import com.natiqhaciyef.util.models.result.InterviewQuestionResult

class FakeInterviewQuestionRepository(
    private val list: MutableList<InterviewQuestionModel>,
) : InterviewQuestionRepository {

    private val remoteInterviewQuestionsResult = InterviewQuestionResult(list)
    private val localInterviewQuestionsList = list

    override suspend fun getAllInterviewQuestion(): InterviewQuestionResult {
        return remoteInterviewQuestionsResult
    }

    override suspend fun insertInterviewQuestion(questionModel: InterviewQuestionModel): CRUDResponse {
        if (remoteInterviewQuestionsResult.list == null)
            return CRUDResponse(success = 0, message = ErrorMessages.NULL_PROPERTY)

        val temp = remoteInterviewQuestionsResult.list!!.toMutableList()
        temp.add(questionModel)
        remoteInterviewQuestionsResult.list = temp
        return if (remoteInterviewQuestionsResult.list!!.contains(questionModel))
            CRUDResponse(success = 1, message = BaseUseCase.INSERT_SUCCESS)
        else
            CRUDResponse(success = 0, message = BaseUseCase.INSERT_FAIL)
    }

    override suspend fun getAllSavedInterviewQuestions(): List<InterviewQuestionModel>? {
        return localInterviewQuestionsList
    }

    override suspend fun saveInterviewQuestion(questionModel: InterviewQuestionModel) {
        localInterviewQuestionsList.add(questionModel)
    }

    override suspend fun removeSavedInterviewQuestion(questionModel: InterviewQuestionModel) {
        localInterviewQuestionsList.remove(questionModel)
    }
}