package com.rogoz208.notesv2.ui.screens.reminders.list

import androidx.lifecycle.LiveData
import com.rogoz208.notesv2.domain.entities.ReminderEntity

class RemindersListContract {

    interface ViewModel {
        val remindersListLiveData: LiveData<List<ReminderEntity>>
        val editingReminderLiveData: LiveData<ReminderEntity>
        val editingReminderPositionLiveData: LiveData<Int>

        fun onEditReminder(reminder: ReminderEntity, position: Int)
        fun onDeleteReminder(reminder: ReminderEntity)
        fun onRemindersUpdated()
    }
}