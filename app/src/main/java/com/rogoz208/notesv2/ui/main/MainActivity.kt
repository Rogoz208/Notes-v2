package com.rogoz208.notesv2.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rogoz208.notesv2.R
import com.rogoz208.notesv2.databinding.ActivityMainBinding
import com.rogoz208.notesv2.ui.screens.notes.list.NotesListFragment
import com.rogoz208.notesv2.ui.screens.reminders.RemindersFragment
import com.rogoz208.notesv2.ui.screens.settings.SettingsFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)

    private val fragmentsMap = mapOf(
        R.id.bottom_menu_item_notes_screen to NotesListFragment(),
        R.id.bottom_menu_item_reminders_screen to RemindersFragment(),
        R.id.bottom_menu_item_settings_screen to SettingsFragment(),
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
                    fragmentsMap[item.itemId]!!
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
