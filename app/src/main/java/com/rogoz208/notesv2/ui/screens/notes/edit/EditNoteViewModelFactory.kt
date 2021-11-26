package com.rogoz208.notesv2.ui.screens.notes.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rogoz208.notesv2.data.App

class EditNoteViewModelFactory(private val app: App) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EditNoteViewModel(app) as T
    }
}