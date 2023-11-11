package com.natiqhaciyef.domain.common.util.classes

import com.natiqhaciyef.domain.domain.utils.PaymentTypes

data class PaymentChoiceModel(
    var type: PaymentTypes,
    var image: Int,
    var isSelected: Boolean,
)