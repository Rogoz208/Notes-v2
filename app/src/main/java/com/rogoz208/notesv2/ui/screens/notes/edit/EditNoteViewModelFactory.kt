package com.rogoz208.notesv2.ui.screens.notes.edit

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rogoz208.notesv2.data.log.MyAnalytics
import com.rogoz208.notesv2.domain.repos.NotesRepo
import com.rogoz208.notesv2.domain.repos.UrlPreviewRepo

class EditNoteViewModelFactory(
    private val context: Context,
    private val notesRepo: NotesRepo,
    private val urlPreviewRepo: UrlPreviewRepo,
    private val analytics: MyAnalytics
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EditNoteViewModel(context, notesRepo, urlPreviewRepo, analytics) as T
    }
}