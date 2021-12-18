package com.rogoz208.notesv2.util

import android.content.Context
import android.location.Address
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
            val address: String = try {
                val addresses: List<Address> =
                    Geocoder(context).getFromLocation(latitude, longitude, maxResults)
                addresses[0].getAddressLine(0)
            } catch (e: Exception) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                e.message.toString()
            }
            handler.post {
                callback.invoke(address)
            }
        }.start()
    }
}