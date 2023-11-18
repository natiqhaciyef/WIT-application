package com.natiqhaciyef.witapplication.repository

import com.natiqhaciyef.domain.domain.repository.ExamRepository
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.util.common.mappers.toExam
import com.natiqhaciyef.util.common.util.objects.DefaultImpl
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.util.models.ExamModel
import com.natiqhaciyef.util.models.result.CRUDResponse
import com.natiqhaciyef.util.models.result.ExamResult

class FakeExamRepository(private val list: MutableList<ExamModel>) : ExamRepository{

    private val remoteExamResult: ExamResult = ExamResult(list)
    private val localExamList = list

    override suspend fun getAllExams(): ExamResult {
        return remoteExamResult
    }

    override suspend fun insertExam(examModel: ExamModel): CRUDResponse {
        if (remoteExamResult.examResult == null)
            return CRUDResponse(success = 0, message = ErrorMessages.NULL_PROPERTY)

        val list = remoteExamResult.examResult?.toMutableList()
        list?.add(examModel)
        remoteExamResult.examResult = list
        return if (remoteExamResult.examResult!!.contains(examModel))
            CRUDResponse(success = 1, message = BaseUseCase.INSERT_SUCCESS)
        else
            CRUDResponse(success = 0, message = BaseUseCase.INSERT_FAIL)
    }

    override suspend fun getAllParticipatedExams(): List<ExamModel>? {
        return localExamList
    }

    override suspend fun participateExam(examModel: ExamModel) {
        localExamList.add(examModel)
    }

    override suspend fun removeParticipation(examModel: ExamModel) {
        localExamList.remove(examModel)
    }
}