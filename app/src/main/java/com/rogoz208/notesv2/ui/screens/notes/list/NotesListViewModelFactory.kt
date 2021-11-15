package com.rogoz208.notesv2.ui.screens.notes.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rogoz208.notesv2.domain.repos.NotesRepo

class NotesListViewModelFactory(private val repo: NotesRepo) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NotesListViewModel(repo) as T
    }
}
