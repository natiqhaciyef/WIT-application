package com.natiqhaciyef.witapplication.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.witapplication.ui.theme.AppDarkBlue
import com.natiqhaciyef.witapplication.ui.theme.AppExtraLightBrown
import com.natiqhaciyef.witapplication.ui.theme.Red


@Composable
fun CustomAlertDialogSample(
    openDialog: MutableState<Boolean>,
    message: String = "",
    messageId: Int = 0,
    onClick: () -> Unit = { },
) {
    Column {
        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    openDialog.value = false
                },
                title = {

                },
                text = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.ErrorOutline,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                                .size(150.dp),
                            tint = Red
                        )

                        if (message.isNotEmpty()) {
                            Text(
                                text = message,
                                fontSize = 20.sp,
                                color = AppDarkBlue,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier,
                                textAlign = TextAlign.Center
                            )
                        } else if (messageId != 0) {
                            Text(
                                text = stringResource(id = messageId),
                                fontSize = 20.sp,
                                color = AppDarkBlue,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier,
                                textAlign = TextAlign.Center
                            )
                        } else {
                            Text(
                                text = ErrorMessages.SOMETHING_WENT_WRONG,
                                fontSize = 20.sp,
                                color = AppDarkBlue,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier,
                                textAlign = TextAlign.Center
                            )
                        }

                    }
                },
                confirmButton = {},
                dismissButton = {},
                shape = RoundedCornerShape(15.dp),
                containerColor = AppExtraLightBrown
            )
        }
    }
}