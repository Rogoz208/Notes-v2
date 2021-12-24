package com.rogoz208.notesv2.ui.screens.reminders.edit

import androidx.lifecycle.LiveData
import com.rogoz208.notesv2.domain.entities.ReminderEntity

class EditReminderContract {

    interface ViewModel {
        val reminderSavedLiveData: LiveData<Boolean>
        val reminderEntityLiveData: LiveData<ReminderEntity>

        fun onReminderSaved(reminder: ReminderEntity?, title: String, detail: String, remindTime: Long, position: Int?)
    }
}