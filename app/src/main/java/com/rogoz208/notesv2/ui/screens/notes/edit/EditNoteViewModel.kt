package com.rogoz208.notesv2.ui.screens.notes.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rogoz208.notesv2.data.log.MyAnalytics
import com.rogoz208.notesv2.domain.entities.NoteEntity
import com.rogoz208.notesv2.domain.repos.NotesRepo
import com.rogoz208.notesv2.domain.repos.RandomActivityRepo

class EditNoteViewModel(
    private val notesRepo: NotesRepo,
    private val randomActivityRepo: RandomActivityRepo,
    private val analytics: MyAnalytics
) : ViewModel(), EditNoteContract.ViewModel {
    private var note: NoteEntity? = null

    override val noteSavedLiveData = MutableLiveData(false)
    override val randomActivityLiveData = MutableLiveData<String>()
    override val errorMessageLiveData = MutableLiveData<String>()
    override val imageUrlLiveData = MutableLiveData<String>()

    override fun onNoteSaved(note: NoteEntity?, title: String, detail: String, position: Int?) {
        if (note == null && (title != "" || detail != "")) {
            this.note = NoteEntity(null, title, detail, null)
            this.note?.let {
                notesRepo.createNote(it)
                analytics.logEvent("Note \"${it.title}\" is created")
            }
        } else {
            note?.let {
                if (title != "" || detail != "") {
                    notesRepo.updateNote(
                        it.uid.toString(),
                        it.copy(title = title, detail = detail),
                        position!!
                    )
                    analytics.logEvent("Note \"${it.title}\" is saved")
                } else {
                    notesRepo.deleteNote(it.uid.toString())
                    analytics.logEvent("Note \"${it.title}\" is deleted")
                }
            }
        }
        noteSavedLiveData.postValue(true)
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
