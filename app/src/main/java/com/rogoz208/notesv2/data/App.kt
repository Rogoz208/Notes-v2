package com.rogoz208.notesv2.data

import android.app.Application
import com.rogoz208.notesv2.domain.entities.NoteEntity
import com.rogoz208.notesv2.domain.repos.NotesRepo

class App : Application() {
    private val notesRepo: NotesRepo = NotesRepoImpl()

    override fun onCreate() {
        super.onCreate()
        fillRepoByTestValues()
    }

    public fun getNotesRepo(): NotesRepo {
        return notesRepo
    }

    private fun fillRepoByTestValues() {
        for (i in 0..20) {
            notesRepo.createNote(
                NoteEntity(
                    null, "Заметка $i", "Lorem ipsum dolor sit amet, consectetur " +
                            "adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", null
                )
            )
        }
    }
}