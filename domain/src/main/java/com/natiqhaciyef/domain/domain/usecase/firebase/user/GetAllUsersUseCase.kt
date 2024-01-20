package com.natiqhaciyef.domain.domain.usecase.firebase.user

import com.natiqhaciyef.domain.domain.base.BaseUseCase
import com.natiqhaciyef.domain.domain.base.ConfigUseCase
import com.natiqhaciyef.domain.domain.base.USER
import com.natiqhaciyef.domain.domain.base.USER_DIRECTORY
import com.natiqhaciyef.domain.domain.base.USER_WITHOUT_PASSWORD
import com.natiqhaciyef.domain.domain.base.VERIFIED_USER
import com.natiqhaciyef.domain.domain.base.VERIFIED_USER_WITHOUT_PASSWORD
import com.natiqhaciyef.domain.domain.repository.FirebaseRepository
import com.natiqhaciyef.util.common.mappers.mapToWithoutPassword
import com.natiqhaciyef.util.common.mappers.toUserModel
import com.natiqhaciyef.util.common.mappers.toVerifiedUserModel
import com.natiqhaciyef.util.models.UserModel
import com.natiqhaciyef.util.models.UserWithoutPasswordModel
import com.natiqhaciyef.util.models.VerifiedUserModel
import com.natiqhaciyef.util.models.VerifiedUserWithoutPasswordModel
import com.natiqhaciyef.util.models.top.UserAbstraction
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(
    firebaseRepository: FirebaseRepository
) : BaseUseCase<FirebaseRepository>(firebaseRepository) {

    operator fun invoke(
        definer: String = USER_WITHOUT_PASSWORD,
        onSuccess: (List<UserAbstraction>) -> Unit,
        onFail: (Exception?) -> Unit
    ) {
        when (definer) {
            USER -> {
                val list = mutableListOf<UserModel>()
                repository.firestore().collection(USER_DIRECTORY)
                    .addSnapshotListener { value, error ->
                        if (value != null && !value.isEmpty) {
                            val docs = value.documents
                            for (doc in docs) {
                                val user = doc.toUserModel()
                                list.add(user)
                            }

                            onSuccess(list)

                        } else {
                            onFail(Exception(ConfigUseCase.LOADING_FAIL))
                        }
                    }

            }

            USER_WITHOUT_PASSWORD -> {
                val list = mutableListOf<UserWithoutPasswordModel>()
                repository.firestore().collection(USER_DIRECTORY)
                    .addSnapshotListener { value, error ->
                        if (value != null && !value.isEmpty) {
                            val docs = value.documents
                            for (doc in docs) {
                                val user = doc.toUserModel().mapToWithoutPassword()
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

            VERIFIED_USER -> {
                val list = mutableListOf<VerifiedUserModel>()
                repository.firestore().collection(USER_DIRECTORY)
                    .addSnapshotListener { value, error ->
                        if (value != null && !value.isEmpty) {
                            val docs = value.documents
                            for (doc in docs) {
                                val user = doc.toVerifiedUserModel()
                                list.add(user)
                            }

                            onSuccess(list)

                        } else {
                            onFail(Exception(ConfigUseCase.LOADING_FAIL))
                        }
                    }

            }

            VERIFIED_USER_WITHOUT_PASSWORD -> {
                val list = mutableListOf<VerifiedUserWithoutPasswordModel>()
                repository.firestore().collection(USER_DIRECTORY)
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
    }
}