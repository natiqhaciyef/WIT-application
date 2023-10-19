package com.natiqhaciyef.voyagersaz.presentation.component

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Upcoming
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarHalf
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import coil.compose.rememberImagePainter
import com.airbnb.lottie.utils.MiscUtils.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.natiqhaciyef.voyagersaz.data.model.service.PostModel
import com.natiqhaciyef.voyagersaz.presentation.component.fonts.Lobster
import com.natiqhaciyef.witapplication.R
import com.natiqhaciyef.witapplication.common.util.classes.NavItemModel
import com.natiqhaciyef.witapplication.common.util.helpers.cardTypeToImageFinder
import com.natiqhaciyef.witapplication.common.util.helpers.formatExpirationDate
import com.natiqhaciyef.witapplication.common.util.helpers.formatOtherCardNumbers
import com.natiqhaciyef.witapplication.presentation.navigation.ScreenId
import com.natiqhaciyef.witapplication.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue
import kotlin.math.ceil
import kotlin.math.floor

@Composable
fun NavBar(
    selectedIndex: MutableState<Int>,
    list: MutableList<NavItemModel> = mutableListOf(
        NavItemModel(Icons.Default.Home, title = "Home", id = ScreenId.HomeScreen.name),
        NavItemModel(Icons.Default.Explore, title = "Materials", id = ScreenId.OnlineMaterialsScreen.name),
        NavItemModel(Icons.Default.Upcoming, title = "Learn", id = ScreenId.LearnScreen.name),
        NavItemModel(Icons.Default.AccountCircle, title = "Profile", id = ScreenId.UserProfileScreen.name),
    )
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .background(AppDarkBlue, RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            list.forEachIndexed { index, icon ->
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(11f)
                        .clickable { selectedIndex.value = index },
                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        modifier = if (selectedIndex.value == index)
                            Modifier.offset(y = (-8).dp)
                        else
                            Modifier,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .background(
                                    if (selectedIndex.value == index) AppYellow
                                    else Color.Transparent,
                                    shape = CircleShape
                                )
                                .size(45.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = icon.icon,
                                contentDescription = "content",
                                modifier = Modifier.size(25.dp),
                                tint = if (selectedIndex.value == index) Color.White else AppExtraLightBrown
                            )
                        }
                        AnimatedVisibility(visible = (selectedIndex.value == index)) {
                            Text(
                                text = icon.title,
                                modifier = Modifier,
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CurvedRectangle(
    height: Int = 245,
    curveHeight: Int = 145,
    color: Color = AppDarkBlue
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(height.dp)
    ) {
        val path = Path().apply {
            lineTo(0f, size.height - curveHeight)
            cubicTo(
                0f,
                size.height - curveHeight / 2,
                size.width / 2,
                size.height,
                size.width,
                size.height - curveHeight / 2
            )
            lineTo(size.width, 0f)
            close()
        }
        drawPath(
            path = path,
            color = color
        )
    }
}


@Composable
fun InputBoxTitle(
    modifier: Modifier = Modifier
        .padding(horizontal = 20.dp)
        .fillMaxWidth(),
    modifierTitle: Modifier = Modifier
        .padding(horizontal = 20.dp)
        .fillMaxWidth(),
    concept: String,
    input: MutableState<String>,
    isSingleLine: Boolean,
    type: KeyboardType = KeyboardType.Text,
    prefix: String = "",
    icon: ImageVector? = null,
    isBottomShadowActive: Boolean = true,
    onClick: (String) -> Unit = { }
) {
    val focusManager = LocalFocusManager.current

    Text(
        modifier = modifierTitle,
        text = concept,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        textAlign = TextAlign.Start
    )
    Spacer(modifier = Modifier.height(10.dp))

    if (prefix.isNotEmpty()) {
        OutlinedTextField(
            modifier = modifier,
            value = input.value,
            onValueChange = {
                input.value = it
            },
            leadingIcon = {
                if (icon == null)
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email",
                        tint = AppGray
                    )
                else
                    Icon(
                        imageVector = icon,
                        contentDescription = "Icon",
                        tint = AppGray
                    )
            },
            shape = RoundedCornerShape(8.dp),
            enabled = true,
            singleLine = isSingleLine,
            readOnly = false,
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = type,
                imeAction = ImeAction.Next
            ),
            prefix = {
                if (prefix.isNotEmpty()) {
                    Text(
                        text = prefix,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                } else {
                    Text(
                        text = "",
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color.Black,
                focusedBorderColor = AppDarkBlue,
                unfocusedBorderColor = Color.Gray,
                cursorColor = AppDarkBlue,
            ),
            textStyle = TextStyle.Default.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            ),
            placeholder = {
                Text(
                    text = concept,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )
            }
        )
    } else {
        OutlinedTextField(
            modifier = modifier,
            value = input.value,
            onValueChange = {
                input.value = it
            },
            leadingIcon = {
                if (icon == null)
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email",
                        tint = AppGray
                    )
                else
                    Icon(
                        imageVector = icon,
                        contentDescription = "Icon",
                        tint = AppGray
                    )
            },
            shape = RoundedCornerShape(8.dp),
            enabled = true,
            singleLine = isSingleLine,
            readOnly = false,
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = type,
                imeAction = ImeAction.Next
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color.Black,
                focusedBorderColor = AppDarkBlue,
                unfocusedBorderColor = Color.Gray,
                cursorColor = AppDarkBlue,
            ),
            textStyle = TextStyle.Default.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            ),
            placeholder = {
                Text(
                    text = concept,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )
            }
        )
    }
    if (isBottomShadowActive)
        BottomShadow(padding = 23.dp)
}


@Composable
fun InputBoxTitlePassword(
    modifier: Modifier = Modifier
        .padding(horizontal = 20.dp)
        .fillMaxWidth(),
    modifierTitle: Modifier = Modifier
        .padding(start = 20.dp)
        .fillMaxWidth(),
    concept: String,
    input: MutableState<String>,
    passVisibility: MutableState<Boolean>,
    isSingleLine: Boolean,
    type: KeyboardType = KeyboardType.Text,
    prefix: String = "",
    trailingIcon: MutableState<ImageVector?> = mutableStateOf(null),
    leadingIcon: MutableState<ImageVector?> = mutableStateOf(null),
    isBottomShadowActive: Boolean = true,
    onClick: (String) -> Unit = { },
) {
    val focusManager = LocalFocusManager.current
    Text(
        modifier = modifierTitle,
        text = concept,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
    )
    Spacer(modifier = Modifier.height(10.dp))

    if (prefix.isNotEmpty()) {
        OutlinedTextField(
            modifier = modifier,
            value = input.value,
            onValueChange = {
                input.value = it
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email",
                    tint = AppGray
                )
            },
            shape = RoundedCornerShape(8.dp),
            enabled = true,
            singleLine = isSingleLine,
            readOnly = false,
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = type,
                imeAction = ImeAction.Next
            ),
            prefix = {
                if (prefix.isNotEmpty()) {
                    Text(
                        text = prefix,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                } else {
                    Text(
                        text = "",
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                }
            },
            visualTransformation = if (passVisibility.value) VisualTransformation.None
            else PasswordVisualTransformation(),
            trailingIcon = {
                if (trailingIcon.value != null) {
                    Icon(
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(28.dp)
                            .clickable {
                                onClick(input.value)
                            },
                        imageVector = trailingIcon.value!!,
                        contentDescription = "Icon"
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color.Black,
                focusedBorderColor = AppDarkBlue,
                unfocusedBorderColor = Color.Gray,
                cursorColor = AppDarkBlue,
            ),
            textStyle = TextStyle.Default.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            ),
            placeholder = {
                Text(
                    text = concept,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )
            }
        )
    } else {
        OutlinedTextField(
            modifier = modifier,
            value = input.value,
            onValueChange = {
                input.value = it
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email",
                    tint = AppGray
                )
            },
            shape = RoundedCornerShape(8.dp),
            enabled = true,
            singleLine = isSingleLine,
            readOnly = false,
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = type,
                imeAction = ImeAction.Next
            ),
            visualTransformation = if (passVisibility.value) VisualTransformation.None
            else PasswordVisualTransformation(),
            trailingIcon = {
                if (trailingIcon.value != null) {
                    Icon(
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(28.dp)
                            .clickable {
                                onClick(input.value)
                            },
                        imageVector = trailingIcon.value!!,
                        contentDescription = "Icon"
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color.Black,
                focusedBorderColor = AppDarkBlue,
                unfocusedBorderColor = Color.Gray,
                cursorColor = AppDarkBlue,
            ),
            textStyle = TextStyle.Default.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            ),
            placeholder = {
                Text(
                    text = concept,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )
            }
        )
    }

    if (isBottomShadowActive)
        BottomShadow(padding = 23.dp)

}


@Composable
fun InputBoxTitleForCardNumber(
    modifier: Modifier = Modifier,
    modifierInput: Modifier = Modifier,
    concept: String,
    input: MutableState<String>,
    fontSize: Int = 20,
    paymentMethod: MutableState<String>,
    onClick: (String) -> Unit = { }
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        value = input.value,
        onValueChange = {
            if (input.value.length <= 16)
                input.value = it
        },
        enabled = true,
        singleLine = true,
        readOnly = false,
        shape = RoundedCornerShape(8.dp),
        keyboardActions = KeyboardActions(onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        }),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Number
        ),
        textStyle = TextStyle.Default.copy(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        ),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = AppExtraDarkBrown,
            focusedBorderColor = AppExtraLightBrown,
            cursorColor = AppExtraDarkBrown,
            unfocusedTextColor = AppExtraDarkBrown,
            focusedTextColor = AppExtraDarkBrown,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            focusedLabelColor = AppGray,
            unfocusedLabelColor = AppGray,
        ),
        label = {
            Text(
                text = concept,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
        },
        trailingIcon = {
            Image(
                modifier = Modifier
                    .padding(end = 15.dp)
                    .size(35.dp),
                painter = painterResource(id = cardTypeToImageFinder(paymentMethod.value)),
                contentDescription = "Card type"
            )
        },
        visualTransformation = { number ->
            formatOtherCardNumbers(input.value)
        }
    )
}


@Composable
fun InputBoxForCardDateAndCVV(
    modifier: Modifier = Modifier,
    modifierInput: Modifier = Modifier,
    expireDate: MutableState<String>,
    cvvCode: MutableState<String>,
    onClick: (String) -> Unit = { }
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .width(160.dp)
                .padding(end = 15.dp),
            value = expireDate.value,
            onValueChange = {
                if (expireDate.value.length <= 4)
                    expireDate.value = it
            },
            enabled = true,
            singleLine = true,
            readOnly = false,
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            ),
            textStyle = TextStyle.Default.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
            ),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = AppExtraDarkBrown,
                focusedBorderColor = AppExtraLightBrown,
                cursorColor = AppExtraDarkBrown,
                focusedTextColor = AppExtraDarkBrown,
                unfocusedTextColor = AppExtraDarkBrown,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedPlaceholderColor = AppGray,
                unfocusedPlaceholderColor = AppGray,
            ),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.CalendarMonth,
                    contentDescription = "Date"
                )
            },
            label = {
                Text(
                    modifier = Modifier,
                    text = "xx/xx",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )
            },
            visualTransformation = { number ->
                formatExpirationDate(expireDate.value)
            },
        )

        OutlinedTextField(
            modifier = Modifier
                .width(160.dp)
                .padding(start = 15.dp),
            value = cvvCode.value,
            onValueChange = {
                if (cvvCode.value.length < 3)
                    cvvCode.value = it
            },
            enabled = true,
            singleLine = true,
            readOnly = false,
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            ),
            textStyle = TextStyle.Default.copy(
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
            ),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = AppExtraDarkBrown,
                focusedBorderColor = AppExtraLightBrown,
                cursorColor = AppExtraDarkBrown,
                focusedTextColor = AppExtraDarkBrown,
                unfocusedTextColor = AppExtraDarkBrown,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedPlaceholderColor = AppGray,
                unfocusedPlaceholderColor = AppGray,
            ),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Help,
                    contentDescription = "Question"
                )
            },
            label = {
                Text(
                    modifier = Modifier,
                    text = "CVV",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )
            }
        )
    }
}


@Composable
fun InputBox(
    modifier: Modifier = Modifier,
    concept: String,
    input: MutableState<String>,
    isSingleLine: Boolean,
    type: KeyboardType = KeyboardType.Text,
    prefix: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = type,
        imeAction = ImeAction.Next
    ),
    leadingIcon: ImageVector? = null,
    tag: String? = null,
    isBottomShadowActive: Boolean = true,
    onClick: (String) -> Unit = { },
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        value = input.value,
        onValueChange = {
            input.value = it
        },
        leadingIcon = {
            if (leadingIcon != null) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = "Email",
                    tint = AppDarkBlue
                )
            }
        },
        shape = RoundedCornerShape(8.dp),
        enabled = true,
        singleLine = isSingleLine,
        readOnly = false,
        keyboardActions = KeyboardActions(onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        }),
        keyboardOptions = keyboardOptions,
        prefix = {
            if (prefix.isNotEmpty()) {
                Text(
                    text = prefix,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
            } else {
                Text(
                    text = "",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            unfocusedTextColor = Color.Black,
            focusedTextColor = Color.Black,
            focusedBorderColor = AppDarkBlue,
            unfocusedBorderColor = Color.Gray,
            cursorColor = AppDarkBlue,
            focusedTrailingIconColor = AppDarkBlue,
            unfocusedTrailingIconColor = AppDarkBlue,
        ),
        textStyle = TextStyle.Default.copy(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        ),
        placeholder = {
            if (tag == null) {
                Text(
                    text = concept,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )
            } else {
                Text(
                    text = tag,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )
            }
        }
    )
    if (isBottomShadowActive)
        BottomShadow(padding = 23.dp)

}


@Composable
fun InputBoxPassword(
    modifier: Modifier = Modifier,
    concept: String,
    input: MutableState<String>,
    passVisibility: MutableState<Boolean>,
    isSingleLine: Boolean,
    type: KeyboardType = KeyboardType.Text,
    prefix: String = "",
    trailingIcon: MutableState<ImageVector?> = mutableStateOf(null),
    leadingIcon: MutableState<ImageVector?> = mutableStateOf(null),
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        value = input.value,
        onValueChange = {
            input.value = it
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Email",
                tint = AppDarkBlue
            )
        },
        shape = RoundedCornerShape(8.dp),
        enabled = true,
        singleLine = isSingleLine,
        readOnly = false,
        keyboardActions = KeyboardActions(onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        }),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = type,
            imeAction = ImeAction.Next
        ),
        prefix = {
            if (prefix.isNotEmpty()) {
                Text(
                    text = prefix,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
            } else {
                Text(
                    text = "",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
            }
        },
        visualTransformation = if (passVisibility.value) VisualTransformation.None
        else PasswordVisualTransformation(),
        trailingIcon = {
            Icon(
                modifier = Modifier
                    .padding(end = 13.dp)
                    .size(25.dp)
                    .clickable {
                        passVisibility.value = !passVisibility.value
                    },
                imageVector = if (passVisibility.value) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                contentDescription = "Icon",
                tint = AppBrown
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            unfocusedTextColor = Color.Black,
            focusedTextColor = Color.Black,
            focusedBorderColor = AppDarkBlue,
            unfocusedBorderColor = Color.Gray,
            cursorColor = AppDarkBlue,
        ),
        textStyle = TextStyle.Default.copy(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        ),
        placeholder = {
            Text(
                text = concept,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
        }
    )
    BottomShadow(padding = 23.dp)

}


@Composable
fun BottomShadow(
    alpha: Float = 0.1f, height: Dp = 8.dp,
    padding: Dp = 0.dp
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .padding(horizontal = padding)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = alpha),
                        Color.Transparent,
                    )
                )
            )
    )
}

@Composable
fun BottomShadow(modifier: Modifier) {
    Box(
        modifier = modifier
    )
}


@Composable
fun CustomDropDownTitleSelectionBox(
    selectedOption: MutableState<String>,
    title: String,
    nonSelectedOption: String = "Options",
    list: List<String>,
    isEnabled: Boolean = true,
    fontSize: Int = 20
) {
    Text(
        modifier = Modifier
            .padding(start = 20.dp)
            .fillMaxWidth(),
        text = title,
        fontSize = fontSize.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
    )
    Spacer(modifier = Modifier.height(10.dp))
    CustomDropDownMenu(
        title = nonSelectedOption,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        list = list,
        selectedOption = selectedOption,
        isEnabled = true
    )
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomDropDownMenu(
    title: String,
    modifier: Modifier,
    list: List<String>,
    selectedOption: MutableState<String>,
    isEnabled: Boolean = true,
    extraAction: () -> Unit = { }
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier
            .border(
                1.dp,
                AppExtraDarkBrown,
                shape = RoundedCornerShape(10.dp)
            ),
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
    ) {
        androidx.compose.material.TextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
            value = selectedOption.value,
            onValueChange = { },
            textStyle = TextStyle.Default.copy(
                color = AppBrown,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
            ),
            readOnly = true,
            label = {
                Text(
                    text = title,
                    color = AppGray,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                backgroundColor = Color.White,
                textColor = AppBrown,
            )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },

            ) {
            list.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        selectedOption.value = option
                        expanded = false
                    },
                    enabled = isEnabled
                ) {
                    Text(
                        text = option,
                        color = AppExtraDarkBrown,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}


@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double,
    stars: Int = 5,
    starsColor: Color = Color.Yellow,
) {
    val filledStars = floor(rating).toInt()
    val unfilledStars = (stars - ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0))
    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(imageVector = Icons.Outlined.Star, contentDescription = null, tint = starsColor)
        }
        if (halfStar) {
            Icon(
                imageVector = Icons.Outlined.StarHalf,
                contentDescription = null,
                tint = starsColor
            )
        }
        repeat(unfilledStars) {
            Icon(
                imageVector = Icons.Outlined.StarOutline,
                contentDescription = null,
                tint = starsColor
            )
        }
    }
}


@Composable
fun ImageSelection(
    image: MutableState<Uri?>,
    context: Context,
    permissionLauncher: ActivityResultLauncher<String>,
    activityResultLauncher: ActivityResultLauncher<Intent>,
    isPermissionGranted: MutableState<Boolean>,
) {
    Image(
        painter = if (image.value != null) rememberImagePainter(image.value)
        else painterResource(id = R.drawable.non),
        contentDescription = "Image",
        modifier = Modifier
            .size(200.dp)
            .clickable {
                imageSelector(
                    context = context,
                    permissionLauncher = permissionLauncher,
                    activityResultLauncher = activityResultLauncher,
                    isPermissionGranted = isPermissionGranted
                )
            },
        contentScale = if (image.value != null) ContentScale.Crop
        else ContentScale.Fit
    )
}


@SuppressLint("RestrictedApi")
@ExperimentalPagerApi
@Composable
fun CustomViewPager(list: MutableList<PostModel> = mutableListOf()) {
    val pagerState = rememberPagerState(
        initialPage = 0,
//        pageCount = list.size
    )

    LaunchedEffect(key1 = Unit) {
        while (true) {
            yield()
            delay(4500)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                animationSpec = tween(600)
            )
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier,
        count = list.size
    ) { page ->
        Card(
            modifier = Modifier
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                    lerp(
                        0.85f,
                        1f,
                        1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }
                    alpha = lerp(
                        0.5f,
                        1f,
                        1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
                .fillMaxWidth()
                .height(180.dp)
                .padding(horizontal = 10.dp),
            shape = RoundedCornerShape(15.dp)
        ) {
            val item = list[page]
            ViewPagerItemInside(item)
        }
    }
}

@Composable
fun ViewPagerItemInside(ads: PostModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = rememberImagePainter(ads.image),
            contentDescription = "Ad Image",
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrixGrayLayer))
        )

        Text(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(10.dp),
            text = ads.title,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = Lobster.lobster,
            color = AppExtraLightBrown
        )
    }
}


fun imageSelector(
    context: Context,
    permissionLauncher: ActivityResultLauncher<String>,
    activityResultLauncher: ActivityResultLauncher<Intent>,
    isPermissionGranted: MutableState<Boolean>
) {

    if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        isPermissionGranted.value = true
    } else {
        permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    if (isPermissionGranted.value) {
        val intentToGallery =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        activityResultLauncher.launch(intentToGallery)
    }
}

val colorMatrixGrayLayer = floatArrayOf(
    0.65f, 0f, 0f, 0f, 0f,
    0f, 0.65f, 0f, 0f, 0f,
    0f, 0f, 0.65f, 0f, 0f,
    0f, 0f, 0f, 1f, 0f
)

val colorMatrixLightGrayLayer = floatArrayOf(
    0.7f, 0f, 0f, 0f, 0f,
    0f, 0.7f, 0f, 0f, 0f,
    0f, 0f, 0.7f, 0f, 0f,
    0f, 0f, 0f, 1f, 0f
)

val colorMatrixDarkGrayLayer = floatArrayOf(
    0.5f, 0f, 0f, 0f, 0f,
    0f, 0.5f, 0f, 0f, 0f,
    0f, 0f, 0.5f, 0f, 0f,
    0f, 0f, 0f, 1f, 0f
)

val colorMatrixDarkLayer = floatArrayOf(
    0.35f, 0f, 0f, 0f, 0f,
    0f, 0.35f, 0f, 0f, 0f,
    0f, 0f, 0.35f, 0f, 0f,
    0f, 0f, 0f, 1f, 0f
)