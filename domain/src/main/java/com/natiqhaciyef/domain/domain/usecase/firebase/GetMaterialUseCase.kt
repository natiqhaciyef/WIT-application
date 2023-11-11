package com.natiqhaciyef.domain.domain.usecase.firebase

import android.net.Uri
import com.natiqhaciyef.domain.domain.repository.impl.FirebaseRepositoryImpl
import javax.inject.Inject

class GetMaterialUseCase @Inject constructor(
    private val firebaseRepositoryImpl: FirebaseRepositoryImpl
) {

    operator fun invoke(
        field: String,
        filename: String,
        onSuccess: (Uri?) -> Unit = {},
        onFail: (Exception) -> Unit = {}
    ) {
        firebaseRepositoryImpl.ds.storage.reference
            .child("Materials")
            .child(field)
            .child(filename)
            .downloadUrl
            .addOnSuccessListener(onSuccess)
            .addOnFailureListener(onFail)
    }
}