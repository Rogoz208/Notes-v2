package com.rogoz208.notesv2.domain.repos

import com.rogoz208.notesv2.domain.entities.ReminderEntity

interface RemindersRepo {

    fun getReminders(callback: (List<ReminderEntity>)->Unit)

    fun createReminder(reminder: ReminderEntity)

    fun deleteReminder(uid: String)

    fun updateReminder(uid: String, reminder: ReminderEntity, position: Int)
}