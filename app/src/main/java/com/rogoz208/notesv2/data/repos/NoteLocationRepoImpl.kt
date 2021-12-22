package com.rogoz208.notesv2.data.repos

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.location.LocationManager
import com.rogoz208.notesv2.domain.entities.NoteLocation
import com.rogoz208.notesv2.domain.repos.NoteLocationRepo
import com.rogoz208.notesv2.util.AsyncGeocoder

private const val GPS_UPDATE_DURATION_MS = 1_000L
private const val GPS_UPDATE_DISTANCE_M = 10f

class NoteLocationRepoImpl(private val context: Context) : NoteLocationRepo {

    private val locationManager by lazy { context.getSystemService(LOCATION_SERVICE) as LocationManager }
    private var noteLocation: NoteLocation? = null

    @SuppressLint("MissingPermission")
    override fun getCurrentLocation(callback: (NoteLocation) -> Unit) {
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            GPS_UPDATE_DURATION_MS,
            GPS_UPDATE_DISTANCE_M
        ) { location ->
            AsyncGeocoder(context)
                .getFromLocation(location.latitude, location.longitude, 1) { address ->
                    noteLocation =
                        NoteLocation(location.latitude, location.longitude, address)
                    callback(noteLocation!!)
                }
        }
    }
}
