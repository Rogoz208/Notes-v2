package com.rogoz208.notesv2.ui.screens.notes.list

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rogoz208.notesv2.R
import com.rogoz208.notesv2.data.App
import com.rogoz208.notesv2.databinding.FragmentNotesListBinding
import com.rogoz208.notesv2.domain.entities.NoteEntity
import com.rogoz208.notesv2.domain.repos.NotesRepo
import com.rogoz208.notesv2.ui.screens.notes.edit.EditNoteActivity

class NotesListFragment : Fragment(R.layout.fragment_notes_list), NotesListContract.View {
    private val adapter = NotesAdapter()
    private val binding by viewBinding(FragmentNotesListBinding::bind)

    private lateinit var app: App
    private lateinit var repo: NotesRepo
    private lateinit var presenter: NotesListContract.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = NotesListPresenter()
        presenter.attach(this)
        initRepo()
        initRecyclerView()
        initFloatingActionButton()
    }

    private fun initRepo() {
        app = requireActivity().application as App
        repo = app.getNotesRepo()
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = this.adapter

        adapter.data = repo.notes
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(item: NoteEntity, position: Int) {
                Toast.makeText(requireContext(), "Click on ${item.title}", Toast.LENGTH_SHORT)
                    .show()
                presenter.onEditNote(item)
            }

            override fun onItemLongClick(item: NoteEntity, itemView: View, position: Int) {
                Toast.makeText(requireContext(), "Long click on ${item.title}", Toast.LENGTH_SHORT)
                    .show()
                TODO("Not yet implemented")
            }
        })
    }

    private fun initFloatingActionButton() {
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 || dy < 0 && binding.addNoteFloatingActionButton.isShown) {
                    binding.addNoteFloatingActionButton.hide()
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    binding.addNoteFloatingActionButton.show()
                }
                super.onScrollStateChanged(recyclerView, newState)
            }
        })

        binding.addNoteFloatingActionButton.setOnClickListener {
            Toast.makeText(requireContext(), "Add a new note clicked", Toast.LENGTH_SHORT).show()
            presenter.onAddNote()
        }
    }

    override fun openAddNoteScreen() {
        val intent = Intent(requireContext(), EditNoteActivity::class.java)
        startActivity(intent)
    }

    override fun openEditNoteScreen(note: NoteEntity) {
        val intent = Intent(requireContext(), EditNoteActivity::class.java).apply {
            putExtra(EditNoteActivity.NOTE_EXTRA_KEY, note)
        }
        startActivity(intent)
    }

    override fun deleteNote(note: NoteEntity) {
        TODO("Not yet implemented")
    }
}