package com.rogoz208.notesv2.ui.screens.notes.edit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rogoz208.notesv2.R
import com.rogoz208.notesv2.data.App
import com.rogoz208.notesv2.databinding.ActivityEditNoteBinding
import com.rogoz208.notesv2.domain.entities.NoteEntity

class EditNoteActivity : AppCompatActivity(R.layout.activity_edit_note), EditNoteContract.View {
    companion object {
        const val NOTE_EXTRA_KEY = "NOTE_EXTRA_KEY"
        const val NOTE_POSITION_EXTRA_KEY = "NOTE_POSITION_EXTRA_KEY"
    }

    private lateinit var presenter: EditNoteContract.Presenter

    private val binding by viewBinding(ActivityEditNoteBinding::bind)

    private var note: NoteEntity? = null
    private var position: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = EditNotePresenter()
        presenter.attach(this)
        initToolbar()
        fillViews()
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onBackPressed() {
        saveNote()
        super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun fillViews() {
        note = intent.getParcelableExtra(NOTE_EXTRA_KEY)
        note?.let { note ->
            binding.titleEditText.setText(note.title)
            binding.detailEditText.setText(note.detail)
        }
    }

    private fun saveNote() {
        val app = this.application as App
        var position: Int? = null
        if (intent.hasExtra(NOTE_POSITION_EXTRA_KEY)) {
            position = intent.getIntExtra(NOTE_POSITION_EXTRA_KEY, 0)
        }
        presenter.onNoteSaved(
            note,
            binding.titleEditText.text.toString(),
            binding.detailEditText.text.toString(),
            position,
            app.getNotesRepo()
        )
    }

    override fun closeEditNoteScreen() {
        val intent = Intent()
        setResult(RESULT_OK, intent)
        finish()
    }
}

// TODO: move save button in toolbar menu