package com.rogoz208.notesv2.ui.screens.notes.list

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rogoz208.notesv2.R
import com.rogoz208.notesv2.data.App
import com.rogoz208.notesv2.databinding.FragmentNotesListBinding
import com.rogoz208.notesv2.domain.entities.NoteEntity
import com.rogoz208.notesv2.ui.screens.notes.edit.EditNoteActivity
import com.rogoz208.notesv2.ui.screens.notes.list.recycler.NotesAdapter
import com.rogoz208.notesv2.ui.screens.notes.list.recycler.NotesDiffCallback
import com.rogoz208.notesv2.ui.screens.notes.list.recycler.OnItemClickListener

class NotesListFragment : Fragment(R.layout.fragment_notes_list), NotesListContract.View {
    private val binding by viewBinding(FragmentNotesListBinding::bind)

    private var adapter = NotesAdapter()

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

    private fun showNotePopupMenu(item: NoteEntity, position: Int, view: View) {
        val popupMenu = PopupMenu(requireContext(), view, Gravity.END)
        popupMenu.inflate(R.menu.note_item_popup_menu)
        popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.edit_popup_menu_item -> presenter.onEditNote(item, position)
                R.id.delete_popup_menu_item -> presenter.onDeleteNote(item)
            }
            true
        }
        popupMenu.show()
    }

    override fun initRecyclerView(notes: List<NoteEntity>) {
        adapter.data = notes
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(item: NoteEntity, position: Int) {
                Toast.makeText(requireContext(), "Click on ${item.title}", Toast.LENGTH_SHORT)
                    .show()
                presenter.onEditNote(item, position)
            }

            override fun onItemLongClick(item: NoteEntity, itemView: View, position: Int) {
                Toast.makeText(requireContext(), "Long click on ${item.title}", Toast.LENGTH_SHORT)
                    .show()
                showNotePopupMenu(item, position, itemView)
            }
        })

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
    }

    override fun updateRecyclerView(notes: List<NoteEntity>) {
        val notesDiffCallback = NotesDiffCallback(adapter.data, notes)
        val result = DiffUtil.calculateDiff(notesDiffCallback, true)
        adapter.data = notes
        result.dispatchUpdatesTo(adapter)
    }

    override fun openAddNoteScreen() {
        val intent = Intent(requireContext(), EditNoteActivity::class.java)
        startActivityForResult(intent, 1)
    }

    override fun openEditNoteScreen(note: NoteEntity, position: Int) {
        val intent = Intent(requireContext(), EditNoteActivity::class.java).apply {
            putExtra(EditNoteActivity.NOTE_EXTRA_KEY, note)
            putExtra(EditNoteActivity.NOTE_POSITION_EXTRA_KEY, position)
        }
        startActivityForResult(intent, 1)
    }
}
