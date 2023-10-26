package com.natiqhaciyef.witapplication.presentation.screens.main.home

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.natiqhaciyef.witapplication.R
import com.natiqhaciyef.witapplication.common.util.objects.ErrorMessages
import com.natiqhaciyef.witapplication.data.models.UserModel
import com.natiqhaciyef.witapplication.domain.models.MappedPostModel
import com.natiqhaciyef.witapplication.presentation.component.InputBox
import com.natiqhaciyef.witapplication.presentation.component.PostComponent
import com.natiqhaciyef.witapplication.presentation.component.fonts.Lobster
import com.natiqhaciyef.witapplication.presentation.component.fonts.Opensans
import com.natiqhaciyef.witapplication.presentation.viewmodel.PostViewModel
import com.natiqhaciyef.witapplication.presentation.viewmodel.UserViewModel
import com.natiqhaciyef.witapplication.presentation.viewmodel.state.UIState
import com.natiqhaciyef.witapplication.ui.theme.*

@Composable
fun HomeScreen(
    navController: NavController,
    postViewModel: PostViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel()
) {
    val postState = remember { postViewModel.postUIState }
    val users = remember { userViewModel.userUIState }
    val searchQuery = remember { mutableStateOf("") }
    userViewModel.getUser(users.value.list)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppExtraLightBrown)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HomeTopView(searchQuery = searchQuery, users = users)
        HomeBodyView(searchQuery = searchQuery, postState = postState)
    }
}

@Composable
private fun HomeTopView(
    searchQuery: MutableState<String>, users: MutableState<UIState<UserModel>>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(245.dp)
            .background(AppDarkBlue)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
                .height(55.dp),
        ) {
            Text(modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 20.dp)
                .width(200.dp), text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = AppExtraLightBrown,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        fontFamily = Lobster.lobster,
                    )
                ) {
                    append(stringResource(id = R.string.welcome))
                }

                withStyle(
                    SpanStyle(
                        color = AppYellow,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        fontFamily = Lobster.lobster
                    )
                ) {
                    if (users.value.selectedElement != null) append(users.value.selectedElement!!.name)
                    else append(stringResource(id = R.string.guest))
                }

            })


            Icon(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 30.dp)
                    .size(30.dp),
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notification",
                tint = AppYellow
            )

        }

        Spacer(modifier = Modifier.height(45.dp))
        InputBox(
            concept = "",
            tag = stringResource(id = R.string.search),
            input = searchQuery,
            isSingleLine = true,
            leadingIcon = Icons.Default.Search,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text, autoCorrect = true, imeAction = ImeAction.Search
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(55.dp),
        )
    }


}


@Composable
private fun HomeBodyView(
    searchQuery: MutableState<String>, postState: MutableState<UIState<MappedPostModel>>
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    var count = 10
    val untilLoad =
        remember { postState.value.list.filter { postState.value.list.indexOf(it) < count } }


    if (untilLoad.isNotEmpty()) {
        Column(
            modifier = Modifier
                .padding(top = 245.dp)
                .fillMaxSize()
                .background(AppExtraLightBrown),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                text = stringResource(id = R.string.recent_updates),
                fontFamily = Opensans.opensans,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn(
                modifier = Modifier
                    .animateContentSize(animationSpec = tween(600, 100))
//                .wrapContentHeight()
                    .fillMaxWidth()
                    .heightIn(min = 300.dp, max = screenHeight + 300.dp)
            ) {
                items(untilLoad) { post ->
                    PostComponent(mappedPostModel = post)
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(modifier = Modifier.fillMaxHeight()) {
                Text(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .clickable { count += 10 },
                    text = stringResource(id = R.string.load_more),
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    } else {
        Box(
            modifier = Modifier
                .height(screenHeight / 7 * 4)
                .background(AppExtraLightBrown)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 30.dp)
                    .align(Alignment.Center),
                text = ErrorMessages.APPLICATION_UNDER_THE_TEST,
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }
    }
}
