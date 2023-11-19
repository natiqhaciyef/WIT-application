package com.natiqhaciyef.witapplication.util.classes

import com.natiqhaciyef.domain.common.util.classes.PaymentTypes

data class PaymentChoiceModel(
    var type: PaymentTypes,
    var image: Int,
    var isSelected: Boolean,
)