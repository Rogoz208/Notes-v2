package com.rogoz208.notesv2.ui.screens.reminders.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rogoz208.notesv2.domain.repos.RemindersRepo

class EditReminderViewModelFactory(private val remindersRepo: RemindersRepo) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EditReminderViewModel(remindersRepo) as T
    }
}