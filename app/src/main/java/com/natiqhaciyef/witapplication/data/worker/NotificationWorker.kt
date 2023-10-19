package com.natiqhaciyef.techtive.data.worker

import android.content.Context
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.natiqhaciyef.techtive.data.worker.util.NotificationSender

class NotificationWorker(val context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    companion object {
        var title = ""
        var description = ""
    }

    override fun doWork(): Result {
        NotificationSender.sendNotification(
            context = context,
            title = title,
            desc = description
        )

        return Result.success()
    }
}