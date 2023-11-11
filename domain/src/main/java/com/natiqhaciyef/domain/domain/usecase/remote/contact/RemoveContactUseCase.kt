package com.natiqhaciyef.domain.domain.usecase.remote.contact

import com.natiqhaciyef.data.common.Resource
import com.natiqhaciyef.domain.domain.usecase.config.BaseUseCase
import com.natiqhaciyef.domain.domain.repository.ContactRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoveContactUseCase @Inject constructor(
    private val contactRepository: ContactRepository
){

    suspend operator fun invoke(id: Int) = flow {
        emit(Resource.loading(null))

        val result = contactRepository.removeContact(id)

        if (result.success > 0) {
            emit(Resource.success(BaseUseCase.REMOVE_SUCCESS))
        } else {
            emit(Resource.error(BaseUseCase.REMOVE_FAIL, null))
        }

    }
}