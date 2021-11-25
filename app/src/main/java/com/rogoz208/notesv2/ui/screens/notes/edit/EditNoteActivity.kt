package com.rogoz208.notesv2.ui.screens.notes.edit

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
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
        EditNoteViewModelFactory(app.notesRepo, app.urlPreviewRepo)
    }

    private val binding by viewBinding(ActivityEditNoteBinding::bind)

    private var note: NoteEntity? = null

    @RequiresApi(Build.VERSION_CODES.N)
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

        viewModel.webPageLiveData.observe(this) { webPage ->
            webPage?.let {
                binding.urlPreviewWebView.loadDataWithBaseURL(
                    null,
                    it,
                    "text/html; charset=utf-8",
                    "utf-8",
                    null
                )
            }
            if (webPage == null) {
                binding.urlPreviewWebView.loadUrl("about:blank")
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

    @RequiresApi(Build.VERSION_CODES.N)
    private fun fillViews() {
        note = intent.getParcelableExtra(NOTE_EXTRA_KEY)
        note?.let { note ->
            binding.titleEditText.setText(note.title)
            binding.detailEditText.setText(note.detail)
            viewModel.onNoteDetailsChanged(note.detail)
        }

        binding.detailEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onNoteDetailsChanged(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
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
