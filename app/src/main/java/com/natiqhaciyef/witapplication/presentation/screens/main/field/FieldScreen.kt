package com.natiqhaciyef.witapplication.presentation.screens.main.field

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.natiqhaciyef.util.common.util.objects.DefaultImpl
import com.natiqhaciyef.util.common.util.objects.EnumList
import com.natiqhaciyef.util.models.LearnSectionModel
import com.natiqhaciyef.witapplication.R
import com.natiqhaciyef.witapplication.presentation.component.fonts.Opensans
import com.natiqhaciyef.witapplication.presentation.navigation.NavStandards
import com.natiqhaciyef.witapplication.presentation.navigation.ScreenId
import com.natiqhaciyef.witapplication.ui.theme.AppDarkBlue
import com.natiqhaciyef.witapplication.ui.theme.AppExtraLightBrown


@Composable
fun FieldScreen(
    navController: NavController,
    info: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
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
            fontSize = 22.sp,
            fontFamily = Opensans.opensans
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = stringResource(id = R.string.fields_info),
            fontWeight = FontWeight.SemiBold,
            color = AppDarkBlue,
            fontSize = 16.sp,
            fontFamily = Opensans.opensans
        )

        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(
            modifier = Modifier.height(490.dp)
        ) {
            items(EnumList.fields) { field ->
                FieldComponent(lsm = field) {
                    if (info == NavStandards.MATERIAL) {
                        navController.navigate("${ScreenId.MaterialScreen.name}/${field.title}")
                    } else {
                        navController.navigate("${ScreenId.InterviewQuestionsScreen.name}/${field.title}")
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Preview
@Composable
private fun FieldComponent(
    lsm: LearnSectionModel = DefaultImpl.lsm,
    onClick: () -> Unit = { }
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 5.dp)
            .height(70.dp)
            .clickable {
                onClick()
            },
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
            Spacer(modifier = Modifier.width(15.dp))
            Image(
                modifier = Modifier
                    .size(50.dp)
                    .padding(vertical = 5.dp),
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