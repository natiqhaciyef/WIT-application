package com.natiqhaciyef.domain.domain.usecase.local.contact

import com.natiqhaciyef.domain.domain.base.BaseUseCase
import com.natiqhaciyef.domain.domain.base.ConfigUseCase
import com.natiqhaciyef.util.common.Resource
import com.natiqhaciyef.util.models.ContactModel
import com.natiqhaciyef.domain.domain.repository.ContactRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoveSavedContactUseCase @Inject constructor(
    contactRepository: ContactRepository
): BaseUseCase<ContactRepository>(contactRepository) {

    suspend operator fun invoke(contactModel: ContactModel) = flow {
        emit(Resource.loading(null))

        repository.removeContactLocal(contactModel)
        emit(Resource.success(ConfigUseCase.REMOVE_SUCCESS))
    }

}