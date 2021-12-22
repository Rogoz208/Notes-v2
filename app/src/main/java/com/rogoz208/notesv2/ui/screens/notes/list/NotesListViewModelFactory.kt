package com.rogoz208.notesv2.ui.screens.notes.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rogoz208.notesv2.data.log.MyAnalytics
import com.rogoz208.notesv2.domain.repos.NotesRepo

class NotesListViewModelFactory(
    private val notesRepo: NotesRepo,
    private val analytics: MyAnalytics
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NotesListViewModel(notesRepo, analytics) as T
    }
}
