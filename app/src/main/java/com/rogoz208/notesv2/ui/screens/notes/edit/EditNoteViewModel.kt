package com.rogoz208.notesv2.ui.screens.notes.edit

import android.os.Build
import android.util.Patterns
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rogoz208.notesv2.domain.entities.NoteEntity
import com.rogoz208.notesv2.domain.repos.NotesRepo
import com.rogoz208.notesv2.domain.repos.UrlPreviewRepo

class EditNoteViewModel(
    private val notesRepo: NotesRepo,
    private val urlPreviewRepo: UrlPreviewRepo
) : ViewModel(), EditNoteContract.ViewModel {
    private var note: NoteEntity? = null

    override val noteSavedLiveData = MutableLiveData(false)
    override val webPageLiveData = MutableLiveData<String>()

    override fun onNoteSaved(note: NoteEntity?, title: String, detail: String, position: Int?) {
        if (note == null && (title != "" || detail != "")) {
            this.note = NoteEntity(null, title, detail, null)
            notesRepo.createNote(this.note!!)
        } else {
            note?.let {
                if (title != "" || detail != "") {
                    it.title = title
                    it.detail = detail
                    notesRepo.updateNote(it.uid.toString(), it, position!!)
                } else {
                    notesRepo.deleteNote(it.uid.toString())
                }
            }
        }
        noteSavedLiveData.postValue(true)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onNoteDetailsChanged(noteDetails: String) {
        val url = extractUrl(noteDetails)
        url?.let {
            urlPreviewRepo.getWebPageAsync(url) {
                webPageLiveData.postValue(it)
            }
        }
        if (url == null) {
            webPageLiveData.postValue(null)
        }
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
