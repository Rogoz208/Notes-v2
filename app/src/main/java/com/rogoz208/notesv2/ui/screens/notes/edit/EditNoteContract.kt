package com.rogoz208.notesv2.ui.screens.notes.edit

import androidx.lifecycle.LiveData
import com.rogoz208.notesv2.domain.entities.NoteEntity

class EditNoteContract {

    interface ViewModel {
        val noteSavedLiveData: LiveData<Boolean>
        val randomActivityLiveData: LiveData<String>
        val errorMessageLiveData: LiveData<String>
        val imageUrlLiveData: LiveData<String>
        val showProgressBarLiveData: LiveData<Boolean>

        fun onNoteSaved(note: NoteEntity?, title: String, detail: String, position: Int?)
        fun onGenerateRandomActivity()
        fun onImageUrlChange(imageUrl: String)
    }
}
