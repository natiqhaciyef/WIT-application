package com.natiqhaciyef.witapplication.presentation.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.natiqhaciyef.witapplication.util.StateHolder.selectedIndex
import com.natiqhaciyef.witapplication.presentation.component.NavBar
import com.natiqhaciyef.witapplication.presentation.screens.main.home.HomeScreen
import com.natiqhaciyef.witapplication.presentation.screens.main.learn.LearnScreen
import com.natiqhaciyef.witapplication.presentation.screens.main.user.UserProfileScreen

@Composable
fun MainScreenLine(
    navController: NavController
) {
    val count = remember { mutableIntStateOf(10) }

    Scaffold(
        modifier = Modifier.fillMaxSize()
            .padding(bottom = 45.dp),
        bottomBar = { NavBar(selectedIndex = selectedIndex,) }
    ) {
        it.calculateBottomPadding()
        when (selectedIndex.value) {
            0 -> {
                HomeScreen(navController, count)
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