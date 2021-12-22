package com.rogoz208.notesv2.ui.screens.reminders.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rogoz208.notesv2.domain.entities.ReminderEntity
import com.rogoz208.notesv2.domain.repos.RemindersRepo

class RemindersListViewModel(private val remindersRepo: RemindersRepo) : ViewModel(),
    RemindersListContract.ViewModel {

    override val remindersListLiveData = MutableLiveData<List<ReminderEntity>>()
    override val editingReminderLiveData = MutableLiveData<ReminderEntity>()
    override val editingReminderPositionLiveData = MutableLiveData<Int>()

    init {
        updateReminders()
    }

    override fun onEditReminder(reminder: ReminderEntity, position: Int) {
        editingReminderPositionLiveData.postValue(position)
        editingReminderLiveData.postValue(reminder)
    }

    override fun onDeleteReminder(reminder: ReminderEntity) {
        remindersRepo.deleteReminder(reminder.uid)
        updateReminders()
    }

    override fun onRemindersUpdated() {
        updateReminders()
    }

    private fun updateReminders() {
        remindersRepo.getReminders { reminders ->
            remindersListLiveData.postValue(reminders)
        }
    }
}