package com.rogoz208.notesv2.ui.screens.logs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rogoz208.notesv2.R
import com.rogoz208.notesv2.databinding.FragmentLogsBinding

class LogsFragment : Fragment(R.layout.fragment_logs) {
    private val binding by viewBinding(FragmentLogsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}