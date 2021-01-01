package com.example.levelup.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

open class NetworkStateReciver : BroadcastReceiver(){

    private var listeners: MutableList<NetworkStateReceiverListener> = ArrayList()
    private var connected = false

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.extras == null || context == null) {
            return
        }
        val ni = (context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager).activeNetworkInfo
        connected = ni != null && ni.isConnected
        notifyStateToAll()
    }

    private fun notifyStateToAll() {
        listeners.forEach { listener ->
            notifyState(listener)
        }
    }

    private fun notifyState(listener: NetworkStateReceiverListener) = when {
        connected -> listener.networkAvailable()
        else -> listener.networkUnavailable()
    }

    open fun addListener(l: NetworkStateReceiverListener) {
        listeners.add(l)
        notifyState(l)
    }

    open fun removeListener(l: NetworkStateReceiverListener) {
        listeners.remove(l)
    }

    interface NetworkStateReceiverListener {
        fun networkAvailable()

        fun networkUnavailable()
    }

}
