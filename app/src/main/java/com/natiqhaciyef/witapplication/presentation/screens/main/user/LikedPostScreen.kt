package com.natiqhaciyef.witapplication.presentation.screens.main.user

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.natiqhaciyef.witapplication.R
import com.natiqhaciyef.witapplication.common.util.objects.ErrorMessages
import com.natiqhaciyef.witapplication.presentation.component.PostComponent
import com.natiqhaciyef.witapplication.presentation.component.CustomSnackbar
import com.natiqhaciyef.witapplication.presentation.component.isInternetAvailable
import com.natiqhaciyef.witapplication.presentation.viewmodel.PostViewModel
import com.natiqhaciyef.witapplication.ui.theme.AppExtraLightBrown

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LikedPostScreen(
    navController: NavController,
    postViewModel: PostViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val savedPostsState = remember { postViewModel.savedPostUIState }
    val isNetworkConnected = remember { mutableStateOf(isInternetAvailable(context)) }
    val isTapped = remember { mutableStateOf(false) }

    if (isNetworkConnected.value || isTapped.value) {
        CustomSnackbar(returnMessage = ErrorMessages.INTERNET_CONNECTION_FAILED)
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppExtraLightBrown),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (savedPostsState.value.list.isNotEmpty()) {
                Spacer(modifier = Modifier.height(35.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    text = stringResource(id = R.string.liked_posts),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(20.dp))
                LazyColumn {
                    items(savedPostsState.value.list) { post ->
                        val dismissState = rememberDismissState(
                            confirmStateChange = {
                                if (it == DismissValue.DismissedToStart) {
                                    postViewModel.updatePostRemote(
                                        post.copy(likeCount = post.likeCount - 1),
                                        onFail = {
                                            isTapped.value = !isTapped.value
                                        }
                                    ) {
                                        postViewModel.removeSavedPost(post)
                                    }
                                }
                                true
                            }
                        )

                        SwipeToDismiss(
                            state = dismissState,
                            background = {},
                            directions = setOf(DismissDirection.EndToStart)
                        ) {
                            PostComponent(mappedPostModel = post)
                        }
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(AppExtraLightBrown)
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .padding(bottom = 30.dp)
                            .align(Alignment.Center),
                        text = ErrorMessages.POSTS_NOT_FOUND,
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}