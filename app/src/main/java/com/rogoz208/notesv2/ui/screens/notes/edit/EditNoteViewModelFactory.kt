package com.rogoz208.notesv2.ui.screens.notes.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rogoz208.notesv2.data.log.MyAnalytics
import com.rogoz208.notesv2.domain.repos.NotesRepo

class EditNoteViewModelFactory(
    private val notesRepo: NotesRepo,
    private val analytics: MyAnalytics
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EditNoteViewModel(notesRepo, analytics) as T
    }
}