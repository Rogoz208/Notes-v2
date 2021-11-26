package com.rogoz208.notesv2.data

import android.app.IntentService
import android.content.Intent
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class MyLogService(name: String = "MyLogService") : IntentService(name) {
    companion object {
        private const val TAG = "@@@"
    }

    override fun onHandleIntent(intent: Intent?) {
        val eventMessage = intent?.getStringExtra("eventMessage")
        eventMessage?.let {
            Log.d(TAG, eventMessage)
            writeToFile(eventMessage)
        }
    }

    private fun writeToFile(data: String) {
        val myFile = File(filesDir, "logs.txt")
        val outputStream: FileOutputStream?
        try {
            outputStream = FileOutputStream(myFile, true)
            outputStream.write("$data\n".toByteArray())
        } catch (e: IOException) {
            Log.e("Exception", "File write failed: " + e.toString())
        }
    }
}