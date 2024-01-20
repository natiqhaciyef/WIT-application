package com.natiqhaciyef.domain.domain.usecase.firebase

import android.net.Uri
import com.natiqhaciyef.domain.domain.base.BaseUseCase
import com.natiqhaciyef.domain.domain.base.MATERIAL_DIRECTORY
import com.natiqhaciyef.domain.domain.repository.FirebaseRepository
import javax.inject.Inject

class GetMaterialUseCase @Inject constructor(
    firebaseRepositoryImpl: FirebaseRepository
) : BaseUseCase<FirebaseRepository>(firebaseRepositoryImpl) {

    operator fun invoke(
        field: String,
        filename: String,
        onSuccess: (Uri?) -> Unit = {},
        onFail: (Exception) -> Unit = {}
    ) {
        repository.storage().reference
            .child(MATERIAL_DIRECTORY)
            .child(field)
            .child(filename)
            .downloadUrl
            .addOnSuccessListener(onSuccess)
            .addOnFailureListener(onFail)
    }
}