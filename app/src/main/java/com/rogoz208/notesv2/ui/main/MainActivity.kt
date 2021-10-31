package com.rogoz208.notesv2.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rogoz208.notesv2.R
import com.rogoz208.notesv2.databinding.ActivityMainBinding
import com.rogoz208.notesv2.ui.screens.notes.NotesListFragment
import com.rogoz208.notesv2.ui.screens.reminders.RemindersFragment
import com.rogoz208.notesv2.ui.screens.settings.SettingsFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    companion object {
        private const val NOTES_TAG = "NOTES"
        private const val REMINDERS_TAG = "REMINDERS"
        private const val SETTINGS_TAG = "SETTINGS"
    }

    private val binding by viewBinding(ActivityMainBinding::bind)

    private val tagToFragmentMap = mapOf(
        NOTES_TAG to NotesListFragment(),
        REMINDERS_TAG to RemindersFragment(),
        SETTINGS_TAG to SettingsFragment()
    )

    private val bottomMenuItemToFragmentTagMap = mapOf(
        R.id.bottom_menu_item_notes_screen to NOTES_TAG,
        R.id.bottom_menu_item_reminders_screen to REMINDERS_TAG,
        R.id.bottom_menu_item_settings_screen to SETTINGS_TAG
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbar)
        initBottomNavigation()
        openDefaultScreen(savedInstanceState)
    }

    private fun initBottomNavigation() {
        binding.bottomNavigationView.setOnItemSelectedListener { item: MenuItem ->
            supportFragmentManager
                .beginTransaction()
                .replace(
                    binding.fragmentContainer.id,
                    tagToFragmentMap[bottomMenuItemToFragmentTagMap[item.itemId]]!!,
                    bottomMenuItemToFragmentTagMap[item.itemId]
                )
                .commit()
            true
        }
    }

    private fun openDefaultScreen(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            binding.bottomNavigationView.selectedItemId = R.id.bottom_menu_item_notes_screen
        }
    }
}