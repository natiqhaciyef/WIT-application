package com.natiqhaciyef.util.models.result

import com.google.gson.annotations.SerializedName
import com.natiqhaciyef.util.models.ContactModel

data class ContactResult(
    @SerializedName("contact_table")
    var contactResult: List<ContactModel>?
)
