package com.rogoz208.notesv2.ui.screens.reminders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rogoz208.notesv2.R
import com.rogoz208.notesv2.databinding.FragmentRemindersBinding

class RemindersFragment : Fragment(R.layout.fragment_reminders) {
    private val binding by viewBinding(FragmentRemindersBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
