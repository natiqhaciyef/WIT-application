package com.natiqhaciyef.domain.domain.usecase.firebase.verified_user

import com.natiqhaciyef.domain.domain.base.BaseUseCase
import com.natiqhaciyef.domain.domain.base.ConfigUseCase
import com.natiqhaciyef.domain.domain.base.VERIFIED_USER_DIRECTORY
import com.natiqhaciyef.domain.domain.repository.FirebaseRepository
import com.natiqhaciyef.util.common.mappers.mapToWithoutPassword
import com.natiqhaciyef.util.common.mappers.toVerifiedUserModel
import com.natiqhaciyef.util.models.VerifiedUserWithoutPasswordModel
import javax.inject.Inject

class GetAllVerifiedUsersUseCase @Inject constructor(
    firebaseRepository: FirebaseRepository
): BaseUseCase<FirebaseRepository>(firebaseRepository) {

    operator fun invoke(
        onSuccess: (List<VerifiedUserWithoutPasswordModel>) -> Unit,
        onFail: (Exception?) -> Unit
    ) {
        val list = mutableListOf<VerifiedUserWithoutPasswordModel>()
        repository.firestore().collection(VERIFIED_USER_DIRECTORY)
            .addSnapshotListener { value, error ->
                if (value != null && !value.isEmpty) {
                    val docs = value.documents
                    for (doc in docs) {
                        val user = doc.toVerifiedUserModel().mapToWithoutPassword()
                        user?.let {
                            list.add(user)
                        }
                    }

                    onSuccess(list)
                } else {
                    onFail(Exception(ConfigUseCase.LOADING_FAIL))
                }
            }
    }
}