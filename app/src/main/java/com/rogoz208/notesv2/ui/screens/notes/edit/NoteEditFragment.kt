package com.rogoz208.notesv2.ui.screens.notes.edit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rogoz208.notesv2.R
import com.rogoz208.notesv2.databinding.FragmentNoteEditBinding
import com.rogoz208.notesv2.domain.entities.NoteEntity

private const val ARG_NOTE = "NOTE"

class NoteEditFragment : Fragment(R.layout.fragment_note_edit) {
    companion object {
        @JvmStatic
        fun newInstance(note: NoteEntity) =
            NoteEditFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_NOTE, note)
                }
            }
    }

    private var note: NoteEntity? = null

    private val binding by viewBinding(FragmentNoteEditBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            note = it.getParcelable(ARG_NOTE)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillViews()
    }

    private fun fillViews() {
        note?.let {
            binding.titleEditText.setText(it.title)
            binding.detailEditText.setText(it.detail)
        }
    }
}