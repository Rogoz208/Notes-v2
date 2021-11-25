package com.rogoz208.notesv2.data

import android.app.Application
import android.content.Context
import com.rogoz208.notesv2.domain.entities.NoteEntity
import com.rogoz208.notesv2.domain.repos.NotesRepo
import com.rogoz208.notesv2.domain.repos.UrlPreviewRepo

class App : Application() {
    val notesRepo: NotesRepo by lazy { NotesRepoImpl() }
    val urlPreviewRepo: UrlPreviewRepo by lazy { UrlPreviewRepoImpl() }

    override fun onCreate() {
        super.onCreate()
        fillRepoByTestValues()
    }

    private fun fillRepoByTestValues() {
        for (i in 1..20) {
            notesRepo.createNote(
                NoteEntity(
                    null,
                    "Заметка $i",
                    "Lorem ipsum dolor sit amet, consectetur " +
                            "adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                            " google.com",
                    null
                )
            )
        }
    }
}

val Context.app: App
    get() = applicationContext as App
