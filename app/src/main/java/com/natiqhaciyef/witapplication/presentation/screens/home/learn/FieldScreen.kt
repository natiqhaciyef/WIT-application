package com.natiqhaciyef.witapplication.presentation.screens.home.learn

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.natiqhaciyef.witapplication.R
import com.natiqhaciyef.witapplication.common.util.objects.DefaultImpl
import com.natiqhaciyef.witapplication.data.models.InfoModel
import com.natiqhaciyef.witapplication.data.models.LearnSectionModel
import com.natiqhaciyef.witapplication.ui.theme.AppExtraLightBrown


@Composable
fun FieldScreen(
    navController: NavController,
    info: String
) {
    val fields = listOf(
        LearnSectionModel(
            title = "General programming",
            image = "https://firebasestorage.googleapis.com/v0/b/wit-app-6c770.appspot.com/o/Fields%2Fgeneral_programming.png?alt=media&token=5c3a9f7d-4b70-4228-b788-f392500cb346&_gl=1*z8nn89*_ga*MzcwNDMzNjkzLjE2NzkyMjgzNzk.*_ga_CW55HF8NVT*MTY5ODEzNTczMS4yMTAuMS4xNjk4MTM3ODAzLjQ3LjAuMA.."
        ),
        LearnSectionModel(
            title = "AI",
            image = "https://firebasestorage.googleapis.com/v0/b/wit-app-6c770.appspot.com/o/Fields%2Fai_engineering.png?alt=media&token=335e2479-5260-4eb3-b81b-d6a029bb69ae&_gl=1*h4wmcm*_ga*MzcwNDMzNjkzLjE2NzkyMjgzNzk.*_ga_CW55HF8NVT*MTY5ODEzNTczMS4yMTAuMS4xNjk4MTM3NTk0LjM2LjAuMA.."
        ),
        LearnSectionModel(
            title = "Mobile",
            image = "https://firebasestorage.googleapis.com/v0/b/wit-app-6c770.appspot.com/o/Fields%2Fmobile.png?alt=media&token=3070300a-bac2-49b3-a6b8-b7f4f5c5bb60&_gl=1*mffr83*_ga*MzcwNDMzNjkzLjE2NzkyMjgzNzk.*_ga_CW55HF8NVT*MTY5ODEzNTczMS4yMTAuMS4xNjk4MTM3NjY4LjI2LjAuMA.."
        ),
        LearnSectionModel(
            title = "Frontend",
            image = "https://firebasestorage.googleapis.com/v0/b/wit-app-6c770.appspot.com/o/Fields%2Ffrontend.png?alt=media&token=4f1a549e-5fc9-41ea-b25f-c24eb8360dfc&_gl=1*18g8az7*_ga*MzcwNDMzNjkzLjE2NzkyMjgzNzk.*_ga_CW55HF8NVT*MTY5ODEzNTczMS4yMTAuMS4xNjk4MTM3NjU2LjM4LjAuMA.."
        ),
        LearnSectionModel(
            title = "Backend",
            image = "https://firebasestorage.googleapis.com/v0/b/wit-app-6c770.appspot.com/o/Fields%2Fbackend.png?alt=media&token=fb8bb05c-7a2f-42c5-9cd1-9a7b98eacf0c&_gl=1*2kc2su*_ga*MzcwNDMzNjkzLjE2NzkyMjgzNzk.*_ga_CW55HF8NVT*MTY5ODEzNTczMS4yMTAuMS4xNjk4MTM3NjE0LjE2LjAuMA.."
        ),
        LearnSectionModel(
            title = "Cyber Security",
            image = "https://firebasestorage.googleapis.com/v0/b/wit-app-6c770.appspot.com/o/Fields%2Fcybersecurity.png?alt=media&token=a7b70934-57cb-4d0c-8bcb-e769a8ed763e&_gl=1*151puly*_ga*MzcwNDMzNjkzLjE2NzkyMjgzNzk.*_ga_CW55HF8NVT*MTY5ODEzNTczMS4yMTAuMS4xNjk4MTM3NjM0LjYwLjAuMA.."
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppExtraLightBrown),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = stringResource(id = R.string.fields),
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 20.sp,
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn {
            items(fields) { field ->
                FieldComponent(lsm = field)
            }
        }
    }
}

@Preview
@Composable
private fun FieldComponent(lsm: LearnSectionModel = DefaultImpl.lsm) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(20.dp))
            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 5.dp)
                    .aspectRatio(1f),
                painter = rememberImagePainter(lsm.image ?: ""),
                contentDescription = "Field image"
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp),
                text = lsm.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}