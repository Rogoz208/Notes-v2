package com.rogoz208.notesv2.data.room.reminders

import com.rogoz208.notesv2.domain.entities.ReminderEntity
import com.rogoz208.notesv2.domain.repos.RemindersRepo
import java.util.*

class RoomRemindersRepoImpl(private val reminderDao: ReminderDao) : RemindersRepo {
    override fun getReminders(callback: (List<ReminderEntity>) -> Unit) {
        Thread {
            callback(reminderDao.getReminders())
        }.start()
    }

    override fun createReminder(reminder: ReminderEntity) {
        Thread {
            val newId = UUID.randomUUID().toString()
            reminderDao.add(reminder.copy(uid = newId))
        }.start()
    }

    override fun deleteReminder(uid: String) {
        getReminders { reminders ->
            reminders.forEach { reminder ->
                when (reminder.uid) {
                    uid -> reminderDao.delete(reminder)
                }
            }
        }
    }

    override fun updateReminder(uid: String, reminder: ReminderEntity, position: Int) {
        Thread{
            reminderDao.update(reminder)
        }.start()
    }
}