package com.k1031oct.nfa.util

import android.content.Context
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object NotificationHelper {
    fun scheduleReminder(context: Context, delayMs: Long, title: String, content: String) {
        val data = Data.Builder()
            .putString("title", title)
            .putString("content", content)
            .build()

        val workRequest = OneTimeWorkRequestBuilder<ReminderWorker>()
            .setInitialDelay(delayMs, TimeUnit.MILLISECONDS)
            .setInputData(data)
            .build()

        WorkManager.getInstance(context).enqueue(workRequest)
    }
}
