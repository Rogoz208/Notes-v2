package com.rogoz208.notesv2.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import com.rogoz208.notesv2.R

class NetworkStateReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (!checkNetworkConnection(context)) {
            Toast.makeText(context, context.getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkNetworkConnection(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                Toast.makeText(context, context.getString(R.string.wifi_is_connected), Toast.LENGTH_SHORT).show()
                true
            }
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                Toast.makeText(context, context.getString(R.string.cellular_is_connected), Toast.LENGTH_SHORT).show()
                true
            }
            //for other device how are able to connect with Ethernet
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                Toast.makeText(context, context.getString(R.string.ethernet_is_connected), Toast.LENGTH_SHORT).show()
                true
            }
            //for check internet over Bluetooth
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> {
                Toast.makeText(context, context.getString(R.string.connected_via_bluetooth), Toast.LENGTH_SHORT)
                    .show()
                true
            }
            else -> false
        }
    }
}