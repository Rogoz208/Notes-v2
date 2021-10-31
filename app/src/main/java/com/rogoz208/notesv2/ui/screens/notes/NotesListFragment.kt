package com.rogoz208.notesv2.ui.screens.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.get
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rogoz208.notesv2.R
import com.rogoz208.notesv2.databinding.FragmentNotesListBinding

class NotesListFragment : Fragment(R.layout.fragment_notes_list) {
    private val binding by viewBinding(FragmentNotesListBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}