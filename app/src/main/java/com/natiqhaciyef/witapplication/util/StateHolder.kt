package com.natiqhaciyef.witapplication.util

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

object StateHolder {
    var homeUIRowCounter = 0
    var selectedIndex = mutableStateOf(0)   // nav index

}