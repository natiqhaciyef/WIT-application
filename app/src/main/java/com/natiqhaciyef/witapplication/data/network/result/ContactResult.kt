package com.natiqhaciyef.witapplication.data.network.result

import com.google.gson.annotations.SerializedName
import com.natiqhaciyef.witapplication.data.models.ContactModel

data class ContactResult(
    @SerializedName("contact_table")
    val contactResult: List<ContactModel>?
)
