package com.rogoz208.notesv2.ui.main

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class RequestLocationPermissionDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val activity = activity as MainActivity
            builder.setTitle("Give Notes permission to access location of the device")
                .setMessage("We need access to location of the device to know place where you created a note")
                .setCancelable(false)
                .setPositiveButton("Accept") { dialog, id ->
                    activity.givePermission()
                }
                .setNegativeButton("Denied") { dialog, id ->
                    Toast.makeText(activity, "We will ask later again", Toast.LENGTH_LONG).show()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}