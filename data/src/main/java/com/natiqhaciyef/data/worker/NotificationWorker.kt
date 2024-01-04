package com.natiqhaciyef.data.worker

import android.content.Context
import androidx.core.app.ActivityCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.natiqhaciyef.util.common.worker.NotificationSender

class NotificationWorker(val context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    companion object {
        var title = ""
        var description = ""
        val activityCompat: ActivityCompat? = null
    }

    override fun doWork(): Result {
        NotificationSender.sendNotification(
            context = context,
            title = title,
            desc = description,
            activityCompat = activityCompat
        )

        return Result.success()
    }
}