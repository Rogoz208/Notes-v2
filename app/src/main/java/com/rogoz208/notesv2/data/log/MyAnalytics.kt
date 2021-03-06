package com.rogoz208.notesv2.data.log

import android.content.Context
import android.content.Intent
import java.text.DateFormat
import java.util.*

class MyAnalytics(private val context: Context) {
    companion object {
        const val EVENT_MESSAGE_EXTRA_KEY = "EVENT_MESSAGE_EXTRA_KEY"
    }

    fun logEvent(eventMessage: String) {
        val service = Intent(context, MyLogService::class.java)
        service.putExtra(
            EVENT_MESSAGE_EXTRA_KEY, "${getCurrentTime()} - $eventMessage"
        )
        context.startService(service)
    }

    private fun getCurrentTime(): String {
        val dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM)
        return dateFormat.format(Calendar.getInstance().time)
    }
}
