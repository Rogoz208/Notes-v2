package com.rogoz208.notesv2.ui.screens.notes.edit

import androidx.lifecycle.LiveData
import com.rogoz208.notesv2.domain.entities.NoteEntity

class EditNoteContract {

    interface ViewModel {
        val noteSavedLiveData: LiveData<Boolean>
        val webPageLiveData: LiveData<String>

        fun onNoteSaved(note: NoteEntity?, title: String, detail: String, position: Int?)
        fun onNoteDetailsChanged(noteDetails: String)
    }
}
