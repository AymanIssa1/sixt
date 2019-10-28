package com.example.sixttask.ui

import androidx.appcompat.app.AppCompatActivity
import com.example.sixttask.R
import com.zplesac.connectionbuddy.ConnectionBuddy
import com.zplesac.connectionbuddy.interfaces.ConnectivityChangeListener
import com.zplesac.connectionbuddy.models.ConnectivityEvent
import com.zplesac.connectionbuddy.models.ConnectivityState
import iammert.com.library.ConnectionStatusView
import iammert.com.library.Status

abstract class BaseActivity : AppCompatActivity(), ConnectivityChangeListener {

    override fun onStart() {
        super.onStart()
        ConnectionBuddy.getInstance().registerForConnectivityEvents(this, this)
    }

    override fun onStop() {
        super.onStop()
        ConnectionBuddy.getInstance().unregisterFromConnectivityEvents(this)
    }

    override fun onConnectionChange(event: ConnectivityEvent) {
        connectivityState = event.state
        val connectionStatusView = findViewById<ConnectionStatusView>(R.id.connection_status_view)
        if (event.state.value == ConnectivityState.CONNECTED) {
            if (!isInternetConnectedViewShowed) {
                isInternetConnectedViewShowed = true
                connectionStatusView.setStatus(Status.COMPLETE)
            }
        } else if (event.state.value == ConnectivityState.DISCONNECTED) {
            isInternetConnectedViewShowed = false
            connectionStatusView.setStatus(Status.ERROR)
        }
    }

    companion object {
        private var isInternetConnectedViewShowed = true
        private var connectivityState: ConnectivityState? = null
    }

}
