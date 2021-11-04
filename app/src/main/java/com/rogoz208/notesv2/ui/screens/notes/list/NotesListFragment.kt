package com.rogoz208.notesv2.ui.screens.notes.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rogoz208.notesv2.R
import com.rogoz208.notesv2.data.App
import com.rogoz208.notesv2.databinding.FragmentNotesListBinding
import com.rogoz208.notesv2.domain.entities.NoteEntity
import com.rogoz208.notesv2.domain.repos.NotesRepo

class NotesListFragment : Fragment(R.layout.fragment_notes_list) {
    private val adapter = NotesAdapter()
    private val binding by viewBinding(FragmentNotesListBinding::bind)

    private lateinit var app: App
    private lateinit var repo: NotesRepo

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRepo()
        initRecyclerView()
    }

    private fun initRepo() {
        app = requireActivity().application as App
        repo = app.getNotesRepo()
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = this.adapter

        adapter.data = repo.notes
        adapter.setOnItemClickListener(object: OnItemClickListener {
            override fun onItemClick(item: NoteEntity, position: Int) {
                Toast.makeText(requireContext(), "Click on ${item.title}", Toast.LENGTH_SHORT ).show()
            }

            override fun onItemLongClick(item: NoteEntity, itemView: View, position: Int) {
                Toast.makeText(requireContext(), "Long click on ${item.title}", Toast.LENGTH_SHORT ).show()
            }

        })
    }
}