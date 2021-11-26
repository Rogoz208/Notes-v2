package com.rogoz208.notesv2.ui.screens.notes.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rogoz208.notesv2.data.App
import com.rogoz208.notesv2.domain.repos.NotesRepo

class NotesListViewModelFactory(private val app: App) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NotesListViewModel(app) as T
    }
}
