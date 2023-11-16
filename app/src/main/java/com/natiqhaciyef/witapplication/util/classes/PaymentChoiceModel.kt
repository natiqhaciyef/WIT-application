package com.natiqhaciyef.domain.common.util.classes

data class PaymentChoiceModel(
    var type: com.natiqhaciyef.domain.common.util.classes.PaymentTypes,
    var image: Int,
    var isSelected: Boolean,
)