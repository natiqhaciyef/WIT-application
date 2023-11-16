package com.natiqhaciyef.witapplication.presentation.screens.main.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.natiqhaciyef.util.common.util.helpers.majorStringToDateChanger
import com.natiqhaciyef.util.common.util.objects.DefaultImpl
import com.natiqhaciyef.util.models.mapped.MappedPostModel
import com.natiqhaciyef.witapplication.presentation.component.fonts.Opensans
import com.natiqhaciyef.witapplication.presentation.navigation.ScreenId
import com.natiqhaciyef.witapplication.presentation.viewmodel.PostViewModel
import com.natiqhaciyef.witapplication.ui.theme.*

@Composable
fun DetailsScreen(
    navController: NavController = rememberNavController(),
    postModel: MappedPostModel = DefaultImpl.mappedPost,
    postViewModel: PostViewModel = hiltViewModel(),
) {
    val savedPosts = remember { postViewModel.savedPostUIState }
    var isLiked by remember { mutableStateOf(false) }
    var isContains by remember { mutableStateOf(false) }
    if (savedPosts.value.list.contains(postModel))
        isContains = true

    BackHandler {
        navController.navigate(route = ScreenId.MainScreenLine.name){
            navController.popBackStack(ScreenId.DetailsScreen.name, inclusive = true)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppExtraLightBrown)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            painter = rememberImagePainter(postModel.image),
            contentDescription = "Image",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(25.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .fillMaxWidth(0.7f),
                text = postModel.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Opensans.opensans,
                lineHeight = 25.sp
            )

            Column(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .fillMaxWidth(0.3f)
                    .padding(start = 15.dp, top = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    modifier = Modifier.size(30.dp),
                    onClick = {
                        isContains = !isContains
                        isLiked = !isLiked

                        if (isContains) {
                            postModel.likeCount += 1
                            postViewModel.savePost(postModel)
                            postViewModel.updatePostRemote(postModel) {
                                postViewModel.updateSavedPost(postModel)
                            }
                        } else {
                            postModel.likeCount -= 1
                            postViewModel.updatePostRemote(postModel) {
                                postViewModel.updateSavedPost(postModel) {
                                    println(postModel.likeCount)
                                    postViewModel.removeSavedPost(postModel)
                                }
                            }
                        }

                        postViewModel.getAllPosts()
                        postViewModel.getAllSavedPosts()


                    }
                ) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = if (isContains) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Like",
                        tint = if (isContains) Red else Color.Black
                    )
                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = if (isLiked) "${postModel.likeCount} likes" else "${postModel.likeCount} likes",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    color = AppDarkGray
                )

            }
        }

        Spacer(modifier = Modifier.height(15.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = postModel.description,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = Opensans.opensans,
            color = AppDarkGray
        )

        Spacer(modifier = Modifier.height(25.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = "Published: ${if (majorStringToDateChanger(postModel.publishDate) != null) majorStringToDateChanger(postModel.publishDate) else "not-selected"}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}