package com.natiqhaciyef.util.common.worker

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotificationWorker(val context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    companion object {
        var title = ""
        var description = ""
        var activityCompat: ComponentActivity? = null
    }

    override fun doWork(): Result {
        sendNotification(
            context = context,
            title = title,
            desc = description,
            activityCompat = activityCompat
        )

        return Result.success()
    }
}