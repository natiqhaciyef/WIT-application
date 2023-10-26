package com.natiqhaciyef.witapplication.presentation.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.natiqhaciyef.voyagersaz.common.util.objects.StateHolder.selectedIndex
import com.natiqhaciyef.witapplication.presentation.component.NavBar
import com.natiqhaciyef.witapplication.presentation.screens.main.home.HomeScreen
import com.natiqhaciyef.witapplication.presentation.screens.main.learn.LearnScreen
import com.natiqhaciyef.witapplication.presentation.screens.main.user.UserProfileScreen

@Composable
fun MainScreenLine(
    navController: NavController
) {

    Scaffold(
        modifier = Modifier.fillMaxSize()
            .padding(bottom = 48.dp),
        bottomBar = { NavBar(selectedIndex = selectedIndex,) }
    ) {
        it.calculateBottomPadding()
        when (selectedIndex.value) {
            0 -> {
                HomeScreen(navController)
            }

            1 -> {
                LearnScreen(navController)
            }

            2 -> {
                UserProfileScreen(navController)
            }
        }
    }
}