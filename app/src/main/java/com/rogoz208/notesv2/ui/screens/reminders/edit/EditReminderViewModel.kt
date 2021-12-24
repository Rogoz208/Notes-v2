package com.rogoz208.notesv2.ui.screens.reminders.edit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rogoz208.notesv2.domain.entities.ReminderEntity
import com.rogoz208.notesv2.domain.repos.RemindersRepo

class EditReminderViewModel(private val remindersRepo: RemindersRepo) : ViewModel(),
    EditReminderContract.ViewModel {
    private var reminder: ReminderEntity? = null

    override val reminderSavedLiveData = MutableLiveData(false)

    override fun onReminderSaved(
        reminder: ReminderEntity?,
        title: String,
        detail: String,
        remindTime: Long,
        position: Int?
    ) {
        if (reminder == null && (title != "" || detail != "")) {
            remindersRepo.createReminder(ReminderEntity("", title, detail, remindTime))
            reminderSavedLiveData.postValue(true)
        } else {
            reminder?.let {
                if (title != "" || detail != "") {
                    remindersRepo.updateReminder(
                        it.uid,
                        it.copy(title = title, detail = detail, remindTime = remindTime),
                        position!!
                    )
                    reminderSavedLiveData.postValue(true)
                } else {
                    remindersRepo.deleteReminder(it.uid)
                    reminderSavedLiveData.postValue(true)
                }
            }
        }
    }
}