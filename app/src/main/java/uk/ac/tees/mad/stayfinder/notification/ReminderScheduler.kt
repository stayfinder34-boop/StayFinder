package uk.ac.tees.mad.stayfinder.notification

import android.content.Context
import android.util.Log
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import uk.ac.tees.mad.stayfinder.data.model.Destination
import java.util.concurrent.TimeUnit

class ReminderScheduler (private val context : Context){

    fun scheduleReminder(destination: String){

        val data = workDataOf("destination" to destination)

        val request = OneTimeWorkRequestBuilder<SearchReminderWorker>()
            .setInputData(data)
            .setInitialDelay(1, TimeUnit.HOURS)
            .build()

        Log.d("reminder" , "request build")
        WorkManager.getInstance(context.applicationContext)
            .enqueueUniqueWork(
                "search_reminder",
                ExistingWorkPolicy.REPLACE,
                request
            )
        Log.d("reminder" , "enqueued")
    }
}