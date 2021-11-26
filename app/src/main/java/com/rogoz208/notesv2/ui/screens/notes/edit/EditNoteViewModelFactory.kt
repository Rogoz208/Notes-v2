package com.rogoz208.notesv2.ui.screens.notes.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rogoz208.notesv2.domain.repos.NotesRepo
import com.rogoz208.notesv2.domain.repos.UrlPreviewRepo

class EditNoteViewModelFactory(private val notesRepo: NotesRepo, private val urlPreviewRepo: UrlPreviewRepo) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EditNoteViewModel(notesRepo, urlPreviewRepo) as T
    }
}