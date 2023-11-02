package com.natiqhaciyef.witapplication.data.models.service

data class NotificationModel(
    var title: String,
    var description: String,
    var imageId: Int = 0,
    var isActive: Boolean
)