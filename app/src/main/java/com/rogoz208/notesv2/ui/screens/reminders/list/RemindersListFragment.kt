package com.rogoz208.notesv2.ui.screens.reminders.list

import android.app.Activity
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
import com.rogoz208.notesv2.databinding.FragmentRemindersListBinding
import com.rogoz208.notesv2.domain.entities.ReminderEntity
import com.rogoz208.notesv2.ui.screens.reminders.edit.EditReminderActivity
import com.rogoz208.notesv2.ui.screens.reminders.list.recycler.OnReminderItemClickListener
import com.rogoz208.notesv2.ui.screens.reminders.list.recycler.RemindersAdapter
import com.rogoz208.notesv2.ui.screens.reminders.list.recycler.RemindersDiffCallback

class RemindersListFragment : Fragment(R.layout.fragment_reminders_list) {

    private val binding by viewBinding(FragmentRemindersListBinding::bind)

    private val viewModel: RemindersListContract.ViewModel by viewModels {
        RemindersListViewModelFactory(
            requireContext().app.remindersRepo
        )
    }

    private val adapter = RemindersAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initRecyclerView()
        initFloatingActionButton()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            viewModel.onRemindersUpdated()
        }
    }

    private fun initViewModel() {
        viewModel.remindersListLiveData.observe(viewLifecycleOwner) { reminders ->
            fillRecyclerView(reminders)
        }

        viewModel.editingReminderLiveData.observe(viewLifecycleOwner) { reminders ->
            openEditReminderScreen(reminders)
        }
    }

    private fun initRecyclerView() {
        adapter.setOnItemClickListener(object : OnReminderItemClickListener {
            override fun onItemClick(item: ReminderEntity, position: Int) {
                viewModel.onEditReminder(item, position)
            }

            override fun onItemLongClick(item: ReminderEntity, itemView: View, position: Int) {
                showReminderPopupMenu(item, position, itemView)
            }
        })

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
    }

    private fun initFloatingActionButton() {
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 || dy < 0 && binding.addReminderFloatingActionButton.isShown) {
                    binding.addReminderFloatingActionButton.hide()
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    binding.addReminderFloatingActionButton.show()
                }
                super.onScrollStateChanged(recyclerView, newState)
            }
        })

        binding.addReminderFloatingActionButton.setOnClickListener {
            openEditReminderScreen(null)
        }
    }

    private fun showReminderPopupMenu(item: ReminderEntity, position: Int, view: View) {
        val popupMenu = PopupMenu(requireContext(), view, Gravity.END)
        popupMenu.inflate(R.menu.reminder_item_popup_menu)
        popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.edit_popup_menu_item -> viewModel.onEditReminder(item, position)
                R.id.delete_popup_menu_item -> viewModel.onDeleteReminder(item)
            }
            true
        }
        popupMenu.show()
    }

    private fun fillRecyclerView(reminders: List<ReminderEntity>) {
        val remindersDiffCallback = RemindersDiffCallback(adapter.data, reminders)
        val result = DiffUtil.calculateDiff(remindersDiffCallback, true)
        adapter.data = reminders
        result.dispatchUpdatesTo(adapter)
    }

    private fun openEditReminderScreen(reminder: ReminderEntity?) {
        if (reminder == null) {
            requireActivity().app.analytics.logEvent("Empty reminder is open")
            val intent = Intent(requireContext(), EditReminderActivity::class.java)
            startActivityForResult(intent, 1)
        } else {
            val intent = Intent(requireContext(), EditReminderActivity::class.java).apply {
                requireActivity().app.analytics.logEvent("Reminder \"${reminder.title}\" is open")
                putExtra(EditReminderActivity.REMINDER_EXTRA_KEY, reminder)
                putExtra(
                    EditReminderActivity.REMINDER_POSITION_EXTRA_KEY,
                    viewModel.editingReminderPositionLiveData.value
                )
            }
            startActivityForResult(intent, 1)
        }
    }
}
