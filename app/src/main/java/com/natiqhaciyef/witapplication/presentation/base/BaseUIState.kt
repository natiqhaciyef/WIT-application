package com.natiqhaciyef.witapplication.presentation.base


data class BaseUIState<T>(
    var data: T? = null,
    var list: List<T> = listOf(),
    var isLoading: Boolean = true,
    var isSuccess: Boolean = false,
    var isFail: Boolean = false,
    var isSuccessMessage: String? = null,
    var isFailMessage: String? = null,
    var isFailReason: Exception? = null,
) {


    fun onSuccess(
        action: (T?, String?) -> Unit = { _, _ -> }
    ) {
        action.invoke(data, isSuccessMessage)
    }

    fun onFail(
        action: (T?, Exception?) -> Unit = { _, _ -> }
    ) {
        action.invoke(data, isFailReason)
    }
}
