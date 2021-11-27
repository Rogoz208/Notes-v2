package com.rogoz208.notesv2.ui.screens.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rogoz208.notesv2.R
import com.rogoz208.notesv2.databinding.FragmentSettingsBinding
import com.rogoz208.notesv2.ui.screens.settings.log.LogFragment

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private val binding by viewBinding(FragmentSettingsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.showLogsButton.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(binding.fragmentContainer.id, LogFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}
