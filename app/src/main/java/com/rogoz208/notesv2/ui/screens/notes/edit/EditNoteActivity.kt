package com.rogoz208.notesv2.ui.screens.notes.edit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rogoz208.notesv2.R
import com.rogoz208.notesv2.data.app
import com.rogoz208.notesv2.databinding.ActivityEditNoteBinding
import com.rogoz208.notesv2.domain.entities.NoteEntity

class EditNoteActivity : AppCompatActivity(R.layout.activity_edit_note) {
    companion object {
        const val NOTE_EXTRA_KEY = "NOTE_EXTRA_KEY"
        const val NOTE_POSITION_EXTRA_KEY = "NOTE_POSITION_EXTRA_KEY"
    }

    private val viewModel: EditNoteContract.ViewModel by viewModels {
        EditNoteViewModelFactory(app.notesRepo)
    }

    private val binding by viewBinding(ActivityEditNoteBinding::bind)

    private var note: NoteEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        initToolbar()
        fillViews()
    }

    private fun initViewModel() {
        viewModel.noteSavedLiveData.observe(this) { isNoteSaved ->
            if (isNoteSaved) {
                val intent = Intent()
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onBackPressed() {
        saveNote()
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
        var position: Int? = null
        if (intent.hasExtra(NOTE_POSITION_EXTRA_KEY)) {
            position = intent.getIntExtra(NOTE_POSITION_EXTRA_KEY, 0)
        }
        viewModel.onNoteSaved(
            note,
            binding.titleEditText.text.toString(),
            binding.detailEditText.text.toString(),
            position
        )
    }
}
