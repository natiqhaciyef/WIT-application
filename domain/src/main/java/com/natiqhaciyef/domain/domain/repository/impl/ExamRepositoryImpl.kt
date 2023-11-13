package com.natiqhaciyef.domain.domain.repository.impl

import com.natiqhaciyef.data.network.result.ExamResult
import com.natiqhaciyef.data.source.ExamDataSource
import com.natiqhaciyef.domain.domain.repository.ExamRepository

class ExamRepositoryImpl(val ds: ExamDataSource) : ExamRepository{
    override suspend fun getAllExams(): ExamResult = ds.getAllExams()
}