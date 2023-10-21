package com.natiqhaciyef.witapplication.presentation.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.natiqhaciyef.voyagersaz.common.util.objects.StateHolder.selectedIndex
import com.natiqhaciyef.witapplication.presentation.component.NavBar

@Composable
fun MainScreenLine(
    navController: NavController
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { NavBar(selectedIndex = selectedIndex,) }
    ) {
        it.calculateBottomPadding()
        when (selectedIndex.value) {
            0 -> {
                HomeScreen(navController)
            }

            1 -> {
                MaterialScreen(navController)
            }

            2 -> {
                LearnScreen(navController)
            }

            3 -> {
                UserProfileScreen(navController)
            }
        }
    }
}