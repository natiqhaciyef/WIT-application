package com.natiqhaciyef.domain.domain.usecase.remote.contact

import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.domain.domain.repository.ContactRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllContactUseCase @Inject constructor(
    private val contactRepository: ContactRepository
) {

    suspend operator fun invoke() = flow {
        emit(Resource.loading(null))

        val result = contactRepository.getAllContacts().contactResult
        if (result != null) {
            emit(Resource.success(result))
        } else {
            emit(Resource.error(BaseUseCase.LOADING_FAIL, null))
        }
    }

}