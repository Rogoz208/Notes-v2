package com.rogoz208.notesv2.ui.screens.notes.edit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rogoz208.notesv2.R
import com.rogoz208.notesv2.data.App
import com.rogoz208.notesv2.databinding.ActivityEditNoteBinding
import com.rogoz208.notesv2.domain.entities.NoteEntity
import com.rogoz208.notesv2.domain.repos.NotesRepo

class EditNoteActivity : AppCompatActivity(R.layout.activity_edit_note), EditNoteContract.View {
    companion object {
        const val NOTE_EXTRA_KEY = "NOTE_EXTRA_KEY"
    }

    private lateinit var presenter: EditNoteContract.Presenter

    private val binding by viewBinding(ActivityEditNoteBinding::bind)

    private var note: NoteEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = EditNotePresenter()
        presenter.attach(this)
        initToolbar()
        fillViews()

        binding.saveNoteButton.setOnClickListener {
            val app = this.application as App
            val repo: NotesRepo = app.getNotesRepo()
            presenter.onNoteSaved(
                note,
                binding.titleEditText.text.toString(),
                binding.detailEditText.text.toString(),
                repo
            )
        }
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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

    override fun closeEditNoteScreen() {
        val intent = Intent()
        setResult(RESULT_OK, intent)
        finish()
    }
}
