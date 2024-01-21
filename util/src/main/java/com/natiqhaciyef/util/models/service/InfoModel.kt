package com.natiqhaciyef.util.models.service


data class InfoModel(
    val title: String,
    val description: String,
)

data class InfoLangModel(
    val title: String = "",
    val titleEng: String = "",
    val titleAz: String = "",
    val titleTr: String = "",
    val description: String = "",
    val descriptionEng: String = "",
    val descriptionAz: String = "",
    val descriptionTr: String = "",
)