package com.rogoz208.notesv2.ui.screens.notes.edit

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rogoz208.notesv2.data.log.MyAnalytics
import com.rogoz208.notesv2.domain.entities.NoteEntity
import com.rogoz208.notesv2.domain.repos.NotesRepo

class EditNoteViewModel(
    private val notesRepo: NotesRepo,
    private val analytics: MyAnalytics
) : ViewModel(), EditNoteContract.ViewModel {
    private var note: NoteEntity? = null

    override val noteSavedLiveData = MutableLiveData(false)

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
                    it.title = title
                    it.detail = detail
                    notesRepo.updateNote(it.uid.toString(), it, position!!)
                    analytics.logEvent("Note \"${it.title}\" is saved")
                } else {
                    notesRepo.deleteNote(it.uid.toString())
                    analytics.logEvent("Note \"${it.title}\" is deleted")
                }
            }
        }
        noteSavedLiveData.postValue(true)
    }

    override fun onNoteDetailsChanged(noteDetails: String) {
        // TODO
    }

    private fun extractUrl(input: String): String? {
        var url = input.split(" ").firstOrNull { Patterns.WEB_URL.matcher(it).find() }
        if (url != null) {
            if (!url.contains("https")) {
                url = "https://$url"
            }
        }
        return url
    }
}
