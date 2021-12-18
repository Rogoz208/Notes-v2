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
    private var note: NoteEntity? = null

    override val noteSavedLiveData = MutableLiveData(false)
    override val randomActivityLiveData = MutableLiveData<String>()
    override val errorMessageLiveData = MutableLiveData<String>()
    override val imageUrlLiveData = MutableLiveData<String>()
    override val showProgressBarLiveData = MutableLiveData(false)

    override fun onNoteSaved(note: NoteEntity?, title: String, detail: String, position: Int?) {
        if (note == null && (title != "" || detail != "")) {
            showProgressBarLiveData.postValue(true)
            noteLocationRepo.getCurrentLocation { location ->
                this.note =
                    NoteEntity(
                        "",
                        title,
                        detail,
                        null,
                        location.latitude,
                        location.longitude,
                        location.address
                    )
                this.note?.let {
                    notesRepo.createNote(it)
                    showProgressBarLiveData.postValue(false)
                    noteSavedLiveData.postValue(true)
                    analytics.logEvent("Note \"${it.title}\" is created")
                }
            }
        } else {
            note?.let {
                if (title != "" || detail != "") {
                    notesRepo.updateNote(
                        it.uid,
                        it.copy(title = title, detail = detail),
                        position!!
                    )
                    showProgressBarLiveData.postValue(false)
                    noteSavedLiveData.postValue(true)
                    analytics.logEvent("Note \"${it.title}\" is saved")
                } else {
                    notesRepo.deleteNote(it.uid)
                    showProgressBarLiveData.postValue(false)
                    noteSavedLiveData.postValue(true)
                    analytics.logEvent("Note \"${it.title}\" is deleted")
                }
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
}
