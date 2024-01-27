package com.natiqhaciyef.witapplication.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.natiqhaciyef.witapplication.presentation.component.isInternetAvailable
import com.natiqhaciyef.witapplication.presentation.navigation.AppNavigation
import com.natiqhaciyef.witapplication.presentation.navigation.NetworkLessNavigation
import com.natiqhaciyef.witapplication.ui.theme.WITApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                val context = LocalContext.current
                val isNetworkAvailable =
                    remember { mutableStateOf(isInternetAvailable(context)) }
                if (isNetworkAvailable.value) {
                    AppNavigation(onStop = false)
                } else {
                    NetworkLessNavigation()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onStop() {
        super.onStop()
        setContent {
            AppTheme {
                AppNavigation(true)
            }
        }
    }
}

@Composable
private fun AppTheme(
    content: @Composable () -> Unit,
) {
    WITApplicationTheme {
        // A surface container using the 'background' color from the theme
        content()
    }
}
