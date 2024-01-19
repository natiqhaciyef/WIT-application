package com.natiqhaciyef.util.common.mappers

import com.google.firebase.firestore.DocumentSnapshot
import com.natiqhaciyef.util.common.util.helpers.hashPassword
import com.natiqhaciyef.util.models.UserModel
import com.natiqhaciyef.util.models.VerifiedUserModel
import com.natiqhaciyef.util.models.enums.UserTypes

fun UserModel.toFirebaseMap(): HashMap<String, Any?> {
    val map = hashMapOf<String, Any?>()
    map["id"] = hashPassword(this.email)
    map["name"] = name
    map["email"] = email
    map["password"] = hashPassword(password)
    return map
}

fun DocumentSnapshot.toUserModel(): UserModel {
    return UserModel(
        id = this["id"].toString(),
        name = this["name"].toString(),
        email = this["email"].toString(),
        password = this["password"].toString(),
    )
}


fun VerifiedUserModel.toFirebaseMap(): HashMap<String, Any?> {
    val map = hashMapOf<String, Any?>()
    map["id"] = this.id
    map["name"] = this.name
    map["email"] = this.email
    map["phone"] = this.phone
    map["image"] = this.image
    map["idImage"] = this.idImage
    map["password"] = this.password
    map["type"] = this.type
    return map
}

fun DocumentSnapshot.toVerifiedUserModel(): VerifiedUserModel {
    return VerifiedUserModel(
        id = this["id"].toString(),
        name = this["name"].toString(),
        email = this["email"].toString(),
        phone = this["phone"].toString(),
        image = this["image"].toString(),
        idImage = this["idImage"].toString(),
        password = this["password"].toString(),
        type = this["type"].toString()
    )
}
