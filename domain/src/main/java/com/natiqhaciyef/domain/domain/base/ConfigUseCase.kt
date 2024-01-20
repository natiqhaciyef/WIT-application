package com.natiqhaciyef.domain.domain.base

object ConfigUseCase {
    const val LOADING_FAIL = "Loading failed"
    const val LOADING_SUCCESS = "Loading successfully finished"

    const val INSERT_FAIL = "Insert failed"
    const val INSERT_SUCCESS = "Insert successfully finished"

    const val REMOVE_FAIL = "Remove failed"
    const val REMOVE_SUCCESS = "Remove successfully finished"

    const val UPDATE_FAIL = "Update failed"
    const val UPDATE_SUCCESS = "Update successfully finished"
}

const val PROFILE_DIRECTORY = "Profiles"
const val USER_DIRECTORY = "Users"
const val FAQ_DIRECTORY = "FAQ"
const val MATERIAL_DIRECTORY = "Materials"
const val VERIFIED_USER_DIRECTORY = "VerifiedUsers"
const val NOTIFICATION_DIRECTORY = "Notifications"

const val USER = "UserModel"
const val USER_WITHOUT_PASSWORD = "UserWithoutPasswordModel"
const val VERIFIED_USER = "VerifiedUserModel"
const val VERIFIED_USER_WITHOUT_PASSWORD = "VerifiedUserWithoutPasswordModel"