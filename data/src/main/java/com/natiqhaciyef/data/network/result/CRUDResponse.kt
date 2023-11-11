package com.natiqhaciyef.data.network.result

import com.google.gson.annotations.Expose


data class CRUDResponse(
    @Expose
    val success: Int,
    @Expose
    val message: String
)