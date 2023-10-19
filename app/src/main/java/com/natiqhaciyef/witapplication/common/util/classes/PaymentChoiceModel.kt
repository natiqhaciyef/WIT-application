package com.natiqhaciyef.witapplication.common.util.classes

data class PaymentChoiceModel(
    var type: PaymentTypes,
    var image: Int,
    var isSelected: Boolean,
)