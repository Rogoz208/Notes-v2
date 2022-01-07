package com.rogoz208.notesv2.ui.screens.reminders.edit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rogoz208.notesv2.domain.entities.ReminderEntity
import com.rogoz208.notesv2.domain.repos.RemindersRepo

class EditReminderViewModel(private val remindersRepo: RemindersRepo) : ViewModel(),
    EditReminderContract.ViewModel {

    override val reminderSavedLiveData = MutableLiveData(false)
    override val reminderEntityLiveData = MutableLiveData<ReminderEntity>()

    override fun onReminderSaved(
        reminder: ReminderEntity?,
        title: String,
        detail: String,
        remindTime: Long,
        position: Int?
    ) {
        if (reminder == null) {
            createReminder(title, detail, remindTime)
        } else {
            if (title != "" || detail != "") {
                updateReminder(reminder, title, detail, remindTime, position!!)
            } else {
                deleteReminder(reminder)
            }
        }
    }

    private fun createReminder(title: String, detail: String, remindTime: Long) {
        if (title != "" || detail != "") {
            val newReminder = ReminderEntity("", title, detail, remindTime)
            remindersRepo.createReminder(newReminder)
            reminderEntityLiveData.postValue(newReminder)
            reminderSavedLiveData.postValue(true)
        }
    }

    private fun updateReminder(
        reminder: ReminderEntity,
        title: String,
        detail: String,
        remindTime: Long,
        position: Int
    ) {
        remindersRepo.updateReminder(
            reminder.uid,
            reminder.copy(title = title, detail = detail, remindTime = remindTime),
            position
        )
        reminderEntityLiveData.postValue(
            reminder.copy(
                title = title,
                detail = detail,
                remindTime = remindTime
            )
        )
        reminderSavedLiveData.postValue(true)
    }

    private fun deleteReminder(reminder: ReminderEntity) {
        remindersRepo.deleteReminder(reminder.uid)
        reminderSavedLiveData.postValue(true)
    }
}
