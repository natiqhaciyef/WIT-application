package com.natiqhaciyef.voyagersaz.domain.model

data class MappedPostModel(
    var id: Int,
    var title: String,
    var description: String,
    var image: String,
    var totalExpense: Double,
    var totalExpenseCurrency: String,
    var rating: Double,
    var season: String,
    var place: String,
    var spentDate: String,
    var publishDate: String,
)
