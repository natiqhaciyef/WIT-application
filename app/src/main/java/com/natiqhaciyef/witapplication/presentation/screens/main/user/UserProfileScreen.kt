package com.natiqhaciyef.witapplication.presentation.screens.main.user

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.Assignment
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.ContactSupport
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Tab
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.natiqhaciyef.witapplication.R
import com.natiqhaciyef.witapplication.presentation.component.fonts.Lobster
import com.natiqhaciyef.witapplication.presentation.component.fonts.Opensans
import com.natiqhaciyef.witapplication.presentation.navigation.ScreenId
import com.natiqhaciyef.witapplication.presentation.viewmodel.FirebaseViewModel
import com.natiqhaciyef.witapplication.ui.theme.*


@Composable
fun UserProfileScreen(
    navController: NavController,
    firebaseViewModel: FirebaseViewModel = hiltViewModel(),
) {
    val openDialog = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppExtraLightBrown)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        UserProfileTopView()
        UserProfileMainView(
            navController = navController,
            openDialog = openDialog
        )


        Box(
            modifier = Modifier
                .height(65.dp)
                .background(Color.White)
        )
        if (openDialog.value)
            CustomAlertDialogSample(openDialog = openDialog) {
                firebaseViewModel.signOut()
                navController.navigate(ScreenId.LoginScreen.name) {
                    navController.popBackStack(ScreenId.MainScreenLine.name, inclusive = true)
                }
            }
    }
}


//@Preview
@Composable
fun UserProfileTopView() {
    Icon(
        imageVector = Icons.Default.AccountCircle,
        contentDescription = "User profile icon",
        modifier = Modifier
            .size(200.dp)
            .padding(start = 20.dp, end = 20.dp, top = 40.dp)
            .background(AppExtraLightBrown),
    )
}


@Composable
fun UserProfileMainView(
    navController: NavController,
    openDialog: MutableState<Boolean>,
) {
    Spacer(modifier = Modifier.height(20.dp))

    Column(
        modifier = Modifier
            .background(Color.White)
    ) {
        TitleComponent(titleID = R.string.contents)
        Spacer(modifier = Modifier.height(5.dp))
        SubComponent(
            modifier = Modifier.testTag("User Profile field test tag"),
            navigationId = {
                navController.navigate(ScreenId.LikedPostsScreen.name)
//                navController.navigate(ScreenId.TestScreen.name)
            },
            icon = Icons.Outlined.BookmarkBorder,
            iconSize = 28.dp,
            textId = R.string.liked_posts
        )
        Spacer(modifier = Modifier.height(5.dp))
        SubComponent(
            navigationId = {
                navController.navigate(ScreenId.SavedQuestionsScreen.name)
            },
            icon = Icons.Outlined.Assignment,
            iconSize = 25.dp,
            textId = R.string.saved_questions,
        )
        Spacer(modifier = Modifier.height(5.dp))
        SubComponent(
            navigationId = {
                navController.navigate(ScreenId.LastContactScreen.name)
            },
            icon = Icons.Outlined.Tab,
            iconSize = 24.dp,
            textId = R.string.last_applications
        )


        Spacer(modifier = Modifier.height(5.dp))
        TitleComponent(titleID = R.string.settings)

        Spacer(modifier = Modifier.height(5.dp))
        SubComponent(
            navigationId = {
                navController.navigate(ScreenId.ResetPasswordScreen.name)
            },
            icon = Icons.Outlined.Lock,
            iconSize = 27.dp,
            textId = R.string.reset_password
        )
        Spacer(modifier = Modifier.height(5.dp))
        SubComponent(
            navigationId = {
                navController.navigate(ScreenId.ContactWithUsScreen.name)
            },
            icon = Icons.Outlined.ContactSupport,
            iconSize = 27.dp,
            textId = R.string.contact_with_us
        )
        Spacer(modifier = Modifier.height(5.dp))
        SubComponent(
            navigationId = {
                navController.navigate(ScreenId.VerifyAccountScreen.name)
            },
            icon = Icons.Outlined.Verified,
            iconSize = 27.dp,
            textId = R.string.verify_account
        )

        Spacer(modifier = Modifier.height(5.dp))
        SubComponent(
            navigationId = {
                navController.navigate(ScreenId.LanguageScreen.name)
            },
            icon = Icons.Outlined.Language,
            iconSize = 27.dp,
            textId = R.string.languages
        )

        Spacer(modifier = Modifier.height(5.dp))
        SubComponent(
            navigationId = {
                navController.navigate(ScreenId.HelpScreen.name)
            },
            icon = Icons.Outlined.HelpOutline,
            iconSize = 27.dp,
            textId = R.string.help
        )

        Spacer(modifier = Modifier.height(5.dp))
        SubComponent(
            navigationId = {
                openDialog.value = true
            },
            icon = Icons.Outlined.Logout,
            iconSize = 27.dp,
            textId = R.string.log_out
        )
    }
}


@Composable
private fun TitleComponent(titleID: Int) {
    Box(
        modifier = Modifier
            .height(55.dp)
            .fillMaxWidth()
            .background(AppLightGray)
    ) {
        Text(
            text = stringResource(id = titleID),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .align(Alignment.CenterStart),
            fontWeight = FontWeight.Bold,
            fontSize = 19.sp,
            textAlign = TextAlign.Start
        )
    }
}

@Composable
private fun SubComponent(
    modifier: Modifier = Modifier,
    navigationId: () -> Unit = {},
    icon: ImageVector,
    iconSize: Dp,
    padding: Dp = 0.dp,
    textId: Int,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .clickable {
                navigationId()
            }
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(horizontal = 25.dp)
                .height(50.dp)
                .background(Color.Transparent),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "Component",
                modifier = Modifier
                    .padding(start = padding)
                    .size(iconSize),
                tint = AppDarkBlue
            )

            Spacer(modifier = Modifier.width(20.dp))

            Text(
                text = stringResource(id = textId),
                fontSize = 17.sp,
                color = AppDarkBlue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier,
                textAlign = TextAlign.Center
            )
        }
    }
}


@Composable
private fun SubComponent(
    modifier: Modifier = Modifier,
    navigationId: () -> Unit = {},
    iconId: Int,
    padding: Dp = 0.dp,
    iconSize: Dp,
    textId: Int,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .clickable {
                navigationId()
            }
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(horizontal = 25.dp)
                .height(50.dp)
                .background(Color.Transparent),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = "Component",
                modifier = Modifier
                    .padding(start = padding)
                    .size(iconSize),
                tint = AppDarkBlue
            )

            Spacer(modifier = Modifier.width(20.dp))

            Text(
                text = stringResource(id = textId),
                fontSize = 17.sp,
                color = AppDarkBlue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier,
                textAlign = TextAlign.Center
            )
        }
    }
}


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
                    Text(
                        text = stringResource(id = R.string.alert),
                        fontSize = 18.sp,
                        color = AppDarkBlue,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier,
                        textAlign = TextAlign.Center
                    )
                },
                text = {
                    if (message.isNotEmpty()) {
                        Text(
                            text = message,
                            fontSize = 18.sp,
                            color = AppDarkBlue,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier,
                            textAlign = TextAlign.Center
                        )
                    } else if (messageId != 0) {
                        Text(
                            text = stringResource(id = messageId),
                            fontSize = 18.sp,
                            color = AppDarkBlue,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier,
                            textAlign = TextAlign.Center
                        )
                    }else{
                        Text(
                            text = stringResource(id = R.string.alert_dialog_title),
                            fontSize = 18.sp,
                            color = AppDarkBlue,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier,
                            textAlign = TextAlign.Center
                        )
                    }

                },
                confirmButton = {
                    Button(
                        modifier = Modifier
                            .width(120.dp)
                            .height(55.dp),
                        onClick = {
                            openDialog.value = false
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppDarkBlue
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Transparent)
                        ) {
                            Text(
                                modifier = Modifier
                                    .align(Alignment.Center),
                                text = stringResource(id = R.string.alert_dialog_negative_button),
                                fontSize = 16.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }

                    }

                },
                dismissButton = {
                    Button(
                        modifier = Modifier
                            .width(120.dp)
                            .height(55.dp),
                        onClick = {
                            openDialog.value = false
                            onClick()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppDarkBlue
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Transparent)
                        ) {
                            Text(
                                modifier = Modifier
                                    .align(Alignment.Center),
                                text = stringResource(id = R.string.alert_dialog_positive_button),
                                fontSize = 16.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                },
                shape = RoundedCornerShape(15.dp),
                containerColor = AppExtraLightBrown
            )
        }
    }
}