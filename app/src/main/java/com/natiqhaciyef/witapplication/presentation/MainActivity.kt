package com.natiqhaciyef.witapplication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.natiqhaciyef.witapplication.ui.theme.WITApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {


            }
        }
    }
}

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    WITApplicationTheme {
        // A surface container using the 'background' color from the theme
        content()
    }
}