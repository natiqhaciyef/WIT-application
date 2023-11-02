package com.natiqhaciyef.witapplication.presentation.screens.main.user

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.natiqhaciyef.witapplication.R
import com.natiqhaciyef.witapplication.common.util.objects.EnumList
import com.natiqhaciyef.witapplication.data.models.service.LanguageModel
import com.natiqhaciyef.witapplication.presentation.navigation.ScreenId
import com.natiqhaciyef.witapplication.ui.theme.*
import java.util.Locale

@Composable
fun LanguageScreen(navController: NavController) {
    val context = LocalContext.current

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
            text = stringResource(id = R.string.change_language),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn {
            items(EnumList.languages) { lang ->
                LanguageComponent(language = lang) {
                    setLocaleLang(lang = lang.title, context = context)
                    navController.navigate(ScreenId.MainScreenLine.name) {
                        navController.popBackStack(
                            route = ScreenId.LanguageScreen.name,
                            inclusive = true
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun LanguageComponent(language: LanguageModel, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 5.dp)
            .fillMaxWidth()
            .height(40.dp)
            .clickable {
                onClick()
            },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                modifier = Modifier,
                text = language.detailedName,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = Color.Black,
            )

            Spacer(modifier = Modifier.width(10.dp))
            Text(
                modifier = Modifier,
                text = language.title.ifEmpty { "en" },
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.width(15.dp))
        }
    }
}


fun setLocaleLang(lang: String, context: Context) {
    val locale = Locale(lang)
    Locale.setDefault(locale)
    val resources = context.resources
    val configuration = resources.configuration
    configuration.setLocale(locale)
    resources.updateConfiguration(configuration, resources.displayMetrics)

    val editor = context.getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
    editor.putString("My_Lang", lang)
    editor.apply()
}

fun loadLocale(context: Context) {
    val sharedPreferences = context.getSharedPreferences("Settings", Activity.MODE_PRIVATE)
    val language = sharedPreferences.getString("My_Lang", "")
    setLocaleLang(language!!, context)
}

