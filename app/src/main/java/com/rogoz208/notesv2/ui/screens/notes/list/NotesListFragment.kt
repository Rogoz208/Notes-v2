package com.rogoz208.notesv2.ui.screens.notes.list

import android.app.Activity.RESULT_OK
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
import com.rogoz208.notesv2.ui.screens.notes.list.recycler.NotesAdapter
import com.rogoz208.notesv2.ui.screens.notes.list.recycler.OnItemClickListener

class NotesListFragment : Fragment(R.layout.fragment_notes_list), NotesListContract.View {
    private val binding by viewBinding(FragmentNotesListBinding::bind)

    private lateinit var presenter: NotesListContract.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPresenter()
        initFloatingActionButton()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            presenter.onNotesUpdated()
        }
    }

    private fun initPresenter() {
        presenter = NotesListPresenter(requireActivity().application as App)
        presenter.attach(this)
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

    override fun showNotes(notes: List<NoteEntity>) {
        val adapter = NotesAdapter()
        adapter.data = notes

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

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
    }

    override fun openAddNoteScreen() {
        val intent = Intent(requireContext(), EditNoteActivity::class.java)
        startActivityForResult(intent, 1)
    }

    override fun openEditNoteScreen(note: NoteEntity) {
        val intent = Intent(requireContext(), EditNoteActivity::class.java).apply {
            putExtra(EditNoteActivity.NOTE_EXTRA_KEY, note)
        }
        startActivityForResult(intent, 1)
    }

    override fun deleteNote(note: NoteEntity) {
        TODO("Not yet implemented")
    }
}
