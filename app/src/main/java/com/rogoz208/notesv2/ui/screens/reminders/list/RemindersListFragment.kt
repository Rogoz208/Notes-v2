package com.rogoz208.notesv2.ui.screens.reminders.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rogoz208.notesv2.R
import com.rogoz208.notesv2.databinding.FragmentRemindersListBinding

class RemindersListFragment : Fragment(R.layout.fragment_reminders_list) {
    private val binding by viewBinding(FragmentRemindersListBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
