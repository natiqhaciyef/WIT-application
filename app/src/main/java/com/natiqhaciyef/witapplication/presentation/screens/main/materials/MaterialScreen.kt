package com.natiqhaciyef.witapplication.presentation.screens.main.materials

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.natiqhaciyef.util.common.util.objects.ErrorMessages
import com.natiqhaciyef.witapplication.R
import com.natiqhaciyef.witapplication.presentation.component.ItemFileBox
import com.natiqhaciyef.witapplication.presentation.component.fonts.Opensans
import com.natiqhaciyef.witapplication.ui.theme.*

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MaterialScreen(
    navController: NavController,
    field: String,
    materialsViewModel: MaterialsViewModel = hiltViewModel(),
) {
    materialsViewModel.getAllMaterials(field)
    val materials = remember { materialsViewModel.filesState }
    val stateList = materials.value.list.map {
        mutableStateOf(it)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppExtraLightBrown)
            .padding(bottom = 55.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (materials.value.list.any { it.field.lowercase() == field.lowercase() }) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                text = stringResource(id = R.string.materials),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontFamily = Opensans.opensans
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn {
                items(stateList) { material ->
                    ItemFileBox(material = material)
                }
            }
        } else if (materials.value.isLoading && materials.value.isSuccessMessage == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppExtraLightBrown)
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
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
                        .padding(bottom = 80.dp)
                        .align(Alignment.Center),
                    text = ErrorMessages.DOCUMENT_NOT_FOUND,
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

