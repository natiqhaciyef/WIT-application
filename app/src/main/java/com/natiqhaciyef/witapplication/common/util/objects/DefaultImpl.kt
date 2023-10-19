package com.natiqhaciyef.witapplication.common.util.objects

import android.content.Context
import com.natiqhaciyef.voyagersaz.data.model.enums.PriceCurrencies
import com.natiqhaciyef.voyagersaz.data.model.enums.Season
import com.natiqhaciyef.voyagersaz.data.model.service.PostModel


object DefaultImpl {


    val postModel = PostModel(
        id = 0,
        title = "I visited Vancouver, today",
        description = "Vancouver, a bustling west coast seaport in British Columbia, is among Canada’s densest, most ethnically diverse cities. A popular filming location, it’s surrounded by mountains, and also has thriving art, theatre and music scenes. Vancouver Art Gallery is known for its works by regional artists, while the Museum of Anthropology houses preeminent First Nations collections.",
        image = "https://media.cntraveler.com/photos/61ae4cdb5dbede2a7d001ffa/16:9/w_6000,h_3375,c_limit/Vancouver_Destination%20Vancouver:Albert%20Normandin_86498_Master.jpg",
        totalExpense = 2200.0,
        totalExpenseCurrency = PriceCurrencies.CAD.name,
        rating = 4.5,
        season = Season.Summer.name,
        place = "Vancouver, Canada",
        publishDate = "12.09.2023",
        spentDate = "15 days"
    )

}