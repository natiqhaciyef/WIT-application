package com.natiqhaciyef.witapplication.presentation.screens.home.learn

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.natiqhaciyef.witapplication.data.models.enums.FileTypes
import com.natiqhaciyef.witapplication.ui.theme.*

@Composable
fun MaterialScreen(
    navController: NavController,
    field: String
) {



}



@Composable
fun SnackbarDemo(returnMessage: String) {
    val snackbarHostState = remember { SnackbarHostState() }

    SnackbarHost(
        hostState = snackbarHostState,
        snackbar = {
            Snackbar(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .height(60.dp),
                action = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 10.dp),
                        text = "Okay",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = LightYellow,
                        textAlign = TextAlign.End
                    )
                },
                backgroundColor = AppDarkBlue,
                shape = RoundedCornerShape(8.dp),
            ) {
                Box(modifier = Modifier
                    .fillMaxSize()){
                    Text(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(horizontal = 10.dp)
                            .fillMaxWidth(),
                        text = returnMessage,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    )

    // Show the Snackbar
    LaunchedEffect(key1 = Unit) {
        snackbarHostState.showSnackbar(
            message = returnMessage,
            actionLabel = "Okay",
            duration = SnackbarDuration.Short
        )
    }
}

fun selectImageByType(type: String) = when (type) {
    FileTypes.DOCX.type -> "https://firebasestorage.googleapis.com/v0/b/wit-app-6c770.appspot.com/o/FileTypes%2Fdocx_icon.png?alt=media&token=1c17ca80-1eb7-48c1-8952-70e107284bc8&_gl=1*8a3f6b*_ga*MzcwNDMzNjkzLjE2NzkyMjgzNzk.*_ga_CW55HF8NVT*MTY5ODEyNzExMC4yMDkuMS4xNjk4MTI3MjkwLjYwLjAuMA.."
    FileTypes.PDF.type -> "https://firebasestorage.googleapis.com/v0/b/wit-app-6c770.appspot.com/o/FileTypes%2Fpdf_icon.png?alt=media&token=cdc85d32-bc86-4957-be90-f6ae017e1c09&_gl=1*1reexov*_ga*MzcwNDMzNjkzLjE2NzkyMjgzNzk.*_ga_CW55HF8NVT*MTY5ODEyNzExMC4yMDkuMS4xNjk4MTI3MzQ4LjIuMC4w"
    FileTypes.PNG.type -> "https://firebasestorage.googleapis.com/v0/b/wit-app-6c770.appspot.com/o/FileTypes%2Fpng_icon.png?alt=media&token=cbd25654-cd2a-4cf2-9f9a-07abb17fbc1b&_gl=1*19zuda6*_ga*MzcwNDMzNjkzLjE2NzkyMjgzNzk.*_ga_CW55HF8NVT*MTY5ODEyNzExMC4yMDkuMS4xNjk4MTI3MzY2LjQ0LjAuMA.."
    FileTypes.XLS.type -> "https://firebasestorage.googleapis.com/v0/b/wit-app-6c770.appspot.com/o/FileTypes%2Fxls_icon.png?alt=media&token=270d226a-8ea5-458b-a2ce-94f56993d4ae&_gl=1*wqlmjl*_ga*MzcwNDMzNjkzLjE2NzkyMjgzNzk.*_ga_CW55HF8NVT*MTY5ODEyNzExMC4yMDkuMS4xNjk4MTI3MzkzLjE3LjAuMA.."
    FileTypes.LINK.type -> "https://firebasestorage.googleapis.com/v0/b/wit-app-6c770.appspot.com/o/FileTypes%2Furi_icon.png?alt=media&token=0723fb66-ae46-410f-90c0-b46264175db0&_gl=1*ovj31d*_ga*MzcwNDMzNjkzLjE2NzkyMjgzNzk.*_ga_CW55HF8NVT*MTY5ODEyNzExMC4yMDkuMS4xNjk4MTI3Mzc4LjMyLjAuMA.."
    FileTypes.MP4.type -> "https://firebasestorage.googleapis.com/v0/b/wit-app-6c770.appspot.com/o/FileTypes%2Fmp4_icon.png?alt=media&token=50e46f65-255b-4d0f-87e6-c346bb39f063&_gl=1*idjybp*_ga*MzcwNDMzNjkzLjE2NzkyMjgzNzk.*_ga_CW55HF8NVT*MTY5ODEyNzExMC4yMDkuMS4xNjk4MTI3MzE3LjMzLjAuMA.."
    FileTypes.MP3.type -> "https://firebasestorage.googleapis.com/v0/b/wit-app-6c770.appspot.com/o/FileTypes%2Fmp3_icon.png?alt=media&token=31352d75-a07a-4ae8-bc35-eea6bc65a024&_gl=1*hvzdc*_ga*MzcwNDMzNjkzLjE2NzkyMjgzNzk.*_ga_CW55HF8NVT*MTY5ODEyNzExMC4yMDkuMS4xNjk4MTI3MzA0LjQ2LjAuMA.."
    FileTypes.OTHER.type -> "https://firebasestorage.googleapis.com/v0/b/wit-app-6c770.appspot.com/o/FileTypes%2Fother_file_icon.png?alt=media&token=36e3c505-fb5b-4383-948c-813a61f80728&_gl=1*1vvg4t7*_ga*MzcwNDMzNjkzLjE2NzkyMjgzNzk.*_ga_CW55HF8NVT*MTY5ODEyNzExMC4yMDkuMS4xNjk4MTI3MzM2LjE0LjAuMA.."
    else -> "https://firebasestorage.googleapis.com/v0/b/wit-app-6c770.appspot.com/o/FileTypes%2Fother_file_icon.png?alt=media&token=36e3c505-fb5b-4383-948c-813a61f80728&_gl=1*1vvg4t7*_ga*MzcwNDMzNjkzLjE2NzkyMjgzNzk.*_ga_CW55HF8NVT*MTY5ODEyNzExMC4yMDkuMS4xNjk4MTI3MzM2LjE0LjAuMA.."
}
