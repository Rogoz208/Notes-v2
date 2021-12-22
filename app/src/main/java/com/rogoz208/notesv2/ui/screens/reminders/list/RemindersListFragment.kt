package com.rogoz208.notesv2.ui.screens.reminders.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rogoz208.notesv2.R
import com.rogoz208.notesv2.data.app
import com.rogoz208.notesv2.databinding.FragmentRemindersListBinding

class RemindersListFragment : Fragment(R.layout.fragment_reminders_list) {

    private val binding by viewBinding(FragmentRemindersListBinding::bind)

    private val viewModel: RemindersListContract.ViewModel by viewModels {
        RemindersListViewModelFactory(
            requireContext().app.remindersRepo
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
