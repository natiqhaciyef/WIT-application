package com.natiqhaciyef.data.network.result

import com.google.gson.annotations.SerializedName
import com.natiqhaciyef.util.models.ContactModel

data class ContactResult(
    @SerializedName("contact_table")
    val contactResult: List<ContactModel>?
)
