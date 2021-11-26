package com.rogoz208.notesv2.data

import android.content.Context
import android.content.Intent

class MyAnalytics {

    fun logEvent(context: Context, eventMessage: String) {
        val service = Intent(context, MyLogService::class.java)
        context.startService(service.putExtra("eventMessage", eventMessage))
    }
}