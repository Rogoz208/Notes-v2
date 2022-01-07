package com.rogoz208.notesv2.ui.screens.notes.edit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rogoz208.notesv2.data.log.MyAnalytics
import com.rogoz208.notesv2.domain.entities.NoteEntity
import com.rogoz208.notesv2.domain.repos.NoteLocationRepo
import com.rogoz208.notesv2.domain.repos.NotesRepo
import com.rogoz208.notesv2.domain.repos.RandomActivityRepo

class EditNoteViewModel(
    private val notesRepo: NotesRepo,
    private val randomActivityRepo: RandomActivityRepo,
    private val noteLocationRepo: NoteLocationRepo,
    private val analytics: MyAnalytics
) : ViewModel(), EditNoteContract.ViewModel {

    override val noteSavedLiveData = MutableLiveData(false)
    override val randomActivityLiveData = MutableLiveData<String>()
    override val errorMessageLiveData = MutableLiveData<String>()
    override val imageUrlLiveData = MutableLiveData<String>()
    override val showProgressBarLiveData = MutableLiveData(false)

    override fun onNoteSaved(note: NoteEntity?, title: String, detail: String, position: Int?) {
        if (note == null) {
            createNote(title, detail)
        } else {
            if (title != "" || detail != "") {
                updateNote(note, title, detail, position)
            } else {
                deleteNote(note)
            }
        }
    }

    override fun onGenerateRandomActivity() {
        randomActivityRepo.getRandomActivityAsync(
            onSuccess = { randomActivityEntity ->
                randomActivityLiveData.postValue(randomActivityEntity.activity)
            },
            onError = { error ->
                errorMessageLiveData.postValue(error.message)
            })
    }

    override fun onImageUrlChange(imageUrl: String) {
        imageUrlLiveData.postValue(imageUrl)
    }

    private fun createNote(title: String, detail: String) {
        if (title != "" || detail != "") {
            showProgressBarLiveData.postValue(true)
            noteLocationRepo.getCurrentLocation { location ->
                val note = NoteEntity(
                    "",
                    title,
                    detail,
                    null,
                    location.latitude,
                    location.longitude,
                    location.address
                )
                notesRepo.createNote(note)
                showProgressBarLiveData.postValue(false)
                noteSavedLiveData.postValue(true)
                analytics.logEvent("Note \"${note.title}\" is created")
            }
        }
    }

    private fun updateNote(note: NoteEntity, title: String, detail: String, position: Int?) {
        notesRepo.updateNote(
            note.uid,
            note.copy(title = title, detail = detail),
            position!!
        )
        showProgressBarLiveData.postValue(false)
        noteSavedLiveData.postValue(true)
        analytics.logEvent("Note \"${note.title}\" is saved")
    }

    private fun deleteNote(note: NoteEntity) {
        notesRepo.deleteNote(note.uid)
        showProgressBarLiveData.postValue(false)
        noteSavedLiveData.postValue(true)
        analytics.logEvent("Note \"${note.title}\" is deleted")
    }
}
