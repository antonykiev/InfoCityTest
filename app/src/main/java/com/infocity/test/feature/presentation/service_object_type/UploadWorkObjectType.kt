package com.infocity.test.feature.presentation.service_object_type

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.concurrent.TimeUnit

class UploadWorkObjectType(
    context: Context,
    workerParams: WorkerParameters
): Worker(context, workerParams) {

    override fun doWork(): Result {
        Log.d("infocity", "doWork: start")

        try {
            TimeUnit.SECONDS.sleep(10)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        Log.d("infocity", "doWork: end")

        return Result.success()
    }
}