package com.rogoz208.notesv2.ui.screens.notes.list

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rogoz208.notesv2.data.App
import com.rogoz208.notesv2.data.log.MyAnalytics
import com.rogoz208.notesv2.domain.repos.NotesRepo

class NotesListViewModelFactory(
    private val context: Context,
    private val notesRepo: NotesRepo,
    private val analytics: MyAnalytics
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NotesListViewModel(context, notesRepo, analytics) as T
    }
}
