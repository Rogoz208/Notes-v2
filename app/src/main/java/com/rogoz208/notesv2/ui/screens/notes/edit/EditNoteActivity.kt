package com.rogoz208.notesv2.ui.screens.notes.edit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rogoz208.notesv2.R
import com.rogoz208.notesv2.databinding.ActivityEditNoteBinding
import com.rogoz208.notesv2.domain.entities.NoteEntity

class EditNoteActivity : AppCompatActivity(R.layout.activity_edit_note) {
    private val binding by viewBinding(ActivityEditNoteBinding::bind)

    private var note: NoteEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbar)

        fillViews()
    }

    private fun fillViews() {
        note = intent.getParcelableExtra("NOTE")
        note?.let { note ->
            binding.titleEditText.setText(note.title)
            binding.detailEditText.setText(note.detail)
        }
    }
}
