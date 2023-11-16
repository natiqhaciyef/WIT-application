package com.natiqhaciyef.util.common.util.classes

import com.natiqhaciyef.domain.common.util.classes.PaymentTypes

data class PaymentChoiceModel(
    var type: PaymentTypes,
    var image: Int,
    var isSelected: Boolean,
)