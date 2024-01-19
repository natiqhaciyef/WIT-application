package com.natiqhaciyef.domain.domain.usecase.remote.contact

import com.natiqhaciyef.domain.domain.base.BaseUseCase
import com.natiqhaciyef.domain.domain.base.ConfigUseCase
import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.domain.domain.repository.ContactRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoveContactUseCase @Inject constructor(
    contactRepository: ContactRepository
): BaseUseCase<ContactRepository>(contactRepository) {

    suspend operator fun invoke(id: Int) = flow {
        emit(Resource.loading(null))

        val result = repository.removeContact(id)

        if (result.success > 0) {
            emit(Resource.success(ConfigUseCase.REMOVE_SUCCESS))
        } else {
            emit(Resource.error(ConfigUseCase.REMOVE_FAIL, null))
        }

    }
}