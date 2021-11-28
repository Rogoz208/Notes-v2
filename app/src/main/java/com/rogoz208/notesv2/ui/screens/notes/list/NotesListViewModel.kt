package com.rogoz208.notesv2.ui.screens.notes.list

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rogoz208.notesv2.data.App
import com.rogoz208.notesv2.data.log.MyAnalytics
import com.rogoz208.notesv2.domain.entities.NoteEntity
import com.rogoz208.notesv2.domain.repos.NotesRepo

class NotesListViewModel(
    private val context: Context,
    private val notesRepo: NotesRepo,
    private val analytics: MyAnalytics
) : ViewModel(), NotesListContract.ViewModel {

    override val notesListLiveData = MutableLiveData<List<NoteEntity>>()
    override val editingNoteLiveData = MutableLiveData<NoteEntity>()
    override val editingNotePositionLiveData = MutableLiveData<Int>()

    init {
        notesListLiveData.value = notesRepo.notes
    }

    override fun onEditNote(note: NoteEntity, position: Int) {
        editingNotePositionLiveData.postValue(position)
        editingNoteLiveData.postValue(note)
    }

    override fun onDeleteNote(note: NoteEntity) {
        notesRepo.deleteNote(note.uid.toString())
        analytics.logEvent(context, "Note \"${note.title}\" is deleted")
        notesListLiveData.postValue(notesRepo.notes)
    }

    override fun onNotesUpdated() {
        notesListLiveData.postValue(notesRepo.notes)
    }
}
