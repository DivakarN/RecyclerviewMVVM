package com.sysaxiom.recyclerviewmvvm.handlers.network

import android.net.ConnectivityManager
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.util.Log
import androidx.lifecycle.LiveData

class NetworkHandler(private val context: Context) : LiveData<NetworkConnectionData>() {

    private val networkReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            try {
                val connectivityManager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
                val activeNetwork = connectivityManager?.activeNetwork
                if(activeNetwork!=null){
                    val capabilities = connectivityManager?.getNetworkCapabilities(activeNetwork)
                    if(capabilities!=null){
                        postValue(NetworkConnectionData(true))
                    }else{
                        postValue(
                            NetworkConnectionData(
                                false
                            )
                        )
                    }
                } else{
                    postValue(NetworkConnectionData(false))
                }
            } catch (e: Exception) {
                Log.d("NetworkHandler", e.toString())
            }
        }

    }

    override fun onActive() {
        super.onActive()
        val filter = IntentFilter()
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        context.registerReceiver(networkReceiver, filter)
    }

    override fun onInactive() {
        super.onInactive()
        context.unregisterReceiver(networkReceiver)
    }
}

class NetworkConnectionData(
    var isNetworkAvailable : Boolean
)