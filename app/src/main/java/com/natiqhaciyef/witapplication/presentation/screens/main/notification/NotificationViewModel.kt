package com.natiqhaciyef.witapplication.presentation.screens.main.notification

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.domain.domain.usecase.firebase.GetAllNotificationsUseCase
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.util.common.util.objects.SuccessMessages
import com.natiqhaciyef.util.models.service.NotificationModel
import com.natiqhaciyef.witapplication.presentation.base.BaseUIState
import com.natiqhaciyef.witapplication.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val getAllNotificationsUseCase: GetAllNotificationsUseCase,
) : BaseViewModel() {
    val notificationState = mutableStateOf(BaseUIState<NotificationModel>())

    init {
        getAllNotification()
    }


    private fun getAllNotification() {
        viewModelScope.launch {
            getAllNotificationsUseCase.invoke(
                onSuccess = { notification ->
                    notificationState.value = notificationState.value.copy(
                        list = notification,
                        isLoading = false,
                        data = null,
                        isSuccess = true,
                        isSuccessMessage = SuccessMessages.SUCCESS,
                        isFail = false,
                        isFailMessage = null,
                        isFailReason = null,
                    )
                },
                onFail = { message ->
                    notificationState.value = notificationState.value.copy(
                        isLoading = false,
                        data = null,
                        list = listOf(),
                        isSuccess = false,
                        isSuccessMessage = null,
                        isFail = true,
                        isFailMessage = ErrorMessages.ERROR,
                        isFailReason = Exception(message),
                    )
                },
            )
        }
    }
}
