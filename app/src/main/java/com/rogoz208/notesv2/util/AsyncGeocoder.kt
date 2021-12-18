package com.rogoz208.notesv2.util

import android.content.Context
import android.location.Geocoder
import android.os.Handler
import android.os.Looper
import android.widget.Toast

class AsyncGeocoder(private val context: Context) {
    private val handler = Handler(Looper.getMainLooper())

    fun getFromLocation(
        latitude: Double,
        longitude: Double,
        maxResults: Int,
        callback: (String) -> Unit
    ) {
        Thread {
            val address = try {
                Geocoder(context).getFromLocation(latitude, longitude, maxResults).firstOrNull()
            } catch (e: Exception) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
            handler.post {
                callback.invoke(address.toString())
            }
        }.start()
    }
}