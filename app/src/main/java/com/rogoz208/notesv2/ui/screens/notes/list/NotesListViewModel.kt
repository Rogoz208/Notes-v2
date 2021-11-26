package com.rogoz208.notesv2.ui.screens.notes.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rogoz208.notesv2.data.App
import com.rogoz208.notesv2.domain.entities.NoteEntity
import java.text.DateFormat
import java.util.*

class NotesListViewModel(private val app: App) : ViewModel(), NotesListContract.ViewModel {

    override val notesListLiveData = MutableLiveData<List<NoteEntity>>()
    override val editingNoteLiveData = MutableLiveData<NoteEntity>()
    override val editingNotePositionLiveData = MutableLiveData<Int>()

    init {
        notesListLiveData.value = app.notesRepo.notes
    }

    override fun onEditNote(note: NoteEntity, position: Int) {
        editingNotePositionLiveData.postValue(position)
        editingNoteLiveData.postValue(note)
    }

    override fun onDeleteNote(note: NoteEntity) {
        app.notesRepo.deleteNote(note.uid.toString())
        app.analytics.logEvent(app, "${getCurrentTime()} - Note \"${note.title}\" is deleted")
        notesListLiveData.postValue(app.notesRepo.notes)
    }

    override fun onNotesUpdated() {
        notesListLiveData.postValue(app.notesRepo.notes)
    }

    private fun getCurrentTime(): String {
        val dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM)
        return dateFormat.format(Calendar.getInstance().time)
    }
}
