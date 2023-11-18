package com.natiqhaciyef.domain.domain.usecase.firebase

import android.net.Uri
import com.natiqhaciyef.domain.domain.repository.FirebaseRepository
import javax.inject.Inject

class GetMaterialUseCase @Inject constructor(
    private val firebaseRepositoryImpl: FirebaseRepository
) {

    operator fun invoke(
        field: String,
        filename: String,
        onSuccess: (Uri?) -> Unit = {},
        onFail: (Exception) -> Unit = {}
    ) {
        firebaseRepositoryImpl.storage().reference
            .child("Materials")
            .child(field)
            .child(filename)
            .downloadUrl
            .addOnSuccessListener(onSuccess)
            .addOnFailureListener(onFail)
    }
}