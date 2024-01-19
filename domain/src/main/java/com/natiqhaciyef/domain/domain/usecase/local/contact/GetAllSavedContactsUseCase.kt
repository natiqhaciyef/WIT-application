package com.natiqhaciyef.domain.domain.usecase.local.contact

import com.natiqhaciyef.domain.domain.base.BaseUseCase
import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.domain.domain.repository.ContactRepository
import com.natiqhaciyef.domain.domain.base.ConfigUseCase
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllSavedContactsUseCase @Inject constructor(
    contactRepository: ContactRepository
): BaseUseCase<ContactRepository>(contactRepository) {

    suspend operator fun invoke() = flow {
        emit(Resource.loading(null))

        val result = repository.getAllSavedContacts()
        if (result != null) {
            emit(Resource.success(result))
        } else {
            emit(Resource.error(ConfigUseCase.LOADING_FAIL, null))
        }
    }

}