package com.natiqhaciyef.witapplication.presentation.viewmodel.state

data class UIState <T>(
    var list: List<T> = listOf(),
    var selectedElement: T? = null,
    var isLoading: Boolean = false,
    var message: String? = null,
)
