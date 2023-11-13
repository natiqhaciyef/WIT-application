package com.natiqhaciyef.domain.domain.repository

import com.natiqhaciyef.data.network.result.ExamResult

interface ExamRepository {

    suspend fun getAllExams(): ExamResult
}