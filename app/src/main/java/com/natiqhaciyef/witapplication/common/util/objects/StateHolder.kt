package com.natiqhaciyef.voyagersaz.common.util.objects

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

object StateHolder {
    var homeUIRowCounter = 0
    var selectedIndex = mutableStateOf(0)   // nav index

}