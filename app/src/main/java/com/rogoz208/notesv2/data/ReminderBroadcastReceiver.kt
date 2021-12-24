package com.rogoz208.notesv2.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.rogoz208.notesv2.R
import com.rogoz208.notesv2.ui.main.REMINDERS_CHANNEL_ID

const val NOTIFICATION_TITLE_EXTRA = "NOTIFICATION_TITLE_EXTRA"
const val NOTIFICATION_CONTENT_EXTRA = "NOTIFICATION_CONTENT_EXTRA"
private const val REMINDER_NOTIFICATION_ID = 1

class ReminderBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            val title = it.getStringExtra(NOTIFICATION_TITLE_EXTRA)
            val text = it.getStringExtra(NOTIFICATION_CONTENT_EXTRA)
            val builder = NotificationCompat.Builder(context!!, REMINDERS_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_reminders)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            val notificationManager = NotificationManagerCompat.from(context)

            notificationManager.notify(REMINDER_NOTIFICATION_ID, builder.build())
        }
    }
}