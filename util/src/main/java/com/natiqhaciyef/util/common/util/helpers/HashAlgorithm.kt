package com.natiqhaciyef.util.common.util.helpers

import java.nio.charset.StandardCharsets
import java.security.MessageDigest

fun hashPassword(password: String): String{
    val messageDigest = MessageDigest.getInstance("SHA-256")
    val hashBytes = messageDigest.digest(password.toByteArray(StandardCharsets.UTF_8))
    val hexString = StringBuffer()

    for (byte in hashBytes){
        hexString.append(String.format("%02x", byte))
    }

    return hexString.toString()
}