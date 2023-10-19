package com.natiqhaciyef.voyagersaz.data.model.service

import com.google.gson.annotations.SerializedName

data class PostModel(
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("image")
    var image: String,
    @SerializedName("total_expense")
    var totalExpense: Double,
    @SerializedName("total_expense_currency")
    var totalExpenseCurrency: String,
    @SerializedName("rating")
    var rating: Double,
    @SerializedName("season")
    var season: String,
    @SerializedName("place")
    var place: String,
    @SerializedName("spent_date")
    var spentDate: String,
    @SerializedName("publish_date")
    var publishDate: String,

)
