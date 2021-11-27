package com.rogoz208.notesv2.ui.screens.notes.list

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rogoz208.notesv2.R
import com.rogoz208.notesv2.data.app
import com.rogoz208.notesv2.databinding.FragmentNotesListBinding
import com.rogoz208.notesv2.domain.entities.NoteEntity
import com.rogoz208.notesv2.ui.screens.notes.edit.EditNoteActivity
import com.rogoz208.notesv2.ui.screens.notes.list.recycler.NotesAdapter
import com.rogoz208.notesv2.ui.screens.notes.list.recycler.NotesDiffCallback
import com.rogoz208.notesv2.ui.screens.notes.list.recycler.OnItemClickListener
import java.text.DateFormat
import java.util.*

class NotesListFragment : Fragment(R.layout.fragment_notes_list) {
    private val binding by viewBinding(FragmentNotesListBinding::bind)

    private val viewModel: NotesListContract.ViewModel by viewModels {
        NotesListViewModelFactory(requireActivity().app)
    }

    private var adapter = NotesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initRecyclerView()
        initFloatingActionButton()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK && requestCode == 1) {
            viewModel.onNotesUpdated()
        }
    }

    private fun initViewModel() {
        viewModel.notesListLiveData.observe(viewLifecycleOwner) { notes ->
            fillRecyclerView(notes)
        }

        viewModel.editingNoteLiveData.observe(viewLifecycleOwner) { note ->
            openEditNoteScreen(note)
        }
    }

    private fun initRecyclerView() {
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(item: NoteEntity, position: Int) {
                viewModel.onEditNote(item, position)
            }

            override fun onItemLongClick(item: NoteEntity, itemView: View, position: Int) {
                showNotePopupMenu(item, position, itemView)
            }
        })

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
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
            openEditNoteScreen(null)
        }
    }

    private fun showNotePopupMenu(item: NoteEntity, position: Int, view: View) {
        val popupMenu = PopupMenu(requireContext(), view, Gravity.END)
        popupMenu.inflate(R.menu.note_item_popup_menu)
        popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.edit_popup_menu_item -> viewModel.onEditNote(item, position)
                R.id.delete_popup_menu_item -> viewModel.onDeleteNote(item)
            }
            true
        }
        popupMenu.show()
    }

    private fun fillRecyclerView(notes: List<NoteEntity>) {
        val notesDiffCallback = NotesDiffCallback(adapter.data, notes)
        val result = DiffUtil.calculateDiff(notesDiffCallback, true)
        adapter.data = notes
        result.dispatchUpdatesTo(adapter)
    }

    private fun openEditNoteScreen(note: NoteEntity?) {
        if (note == null) {
            requireActivity().app.analytics.logEvent(
                requireContext(),
                "Empty note is open"
            )
            val intent = Intent(requireContext(), EditNoteActivity::class.java)
            startActivityForResult(intent, 1)
        } else {
            val intent = Intent(requireContext(), EditNoteActivity::class.java).apply {
                requireActivity().app.analytics.logEvent(
                    requireContext(),
                    "Note \"${note.title}\" is open"
                )
                putExtra(EditNoteActivity.NOTE_EXTRA_KEY, note)
                putExtra(
                    EditNoteActivity.NOTE_POSITION_EXTRA_KEY,
                    viewModel.editingNotePositionLiveData.value
                )
            }
            startActivityForResult(intent, 1)
        }
    }
}
