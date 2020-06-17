package com.sysaxiom.recyclerviewmvvm.handlers.location

import android.content.Context
import android.location.Location
import androidx.lifecycle.LiveData
import android.util.Log
import com.google.android.gms.location.*

const val INTERVAL : Long = 60000

class LocationHandler(private val context: Context) : LiveData<LocationData>(){

    val mFusedLocationClient : FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context.applicationContext)

    override fun onActive() {
        super.onActive()
        val locationRequest: LocationRequest = LocationRequest.create().apply {
            interval = INTERVAL
            fastestInterval = INTERVAL
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }

    override fun onInactive() {
        super.onInactive()
        mFusedLocationClient.removeLocationUpdates(locationCallback)
    }

    private val locationCallback = object : LocationCallback() {

        override fun onLocationResult(locationResult: LocationResult?) {
            try {
                if (locationResult != null) {
                    postValue(
                        LocationData(
                            true,
                            locationResult.getLastLocation()
                        )
                    )
                }
            } catch (e: Exception) {
                Log.d("LocationHandler", e.toString())
            }
        }

        override fun onLocationAvailability(p0: LocationAvailability?) {
            super.onLocationAvailability(p0)
            if(p0?.isLocationAvailable?.equals(true)!!){
                //do nothing
            } else if(p0?.isLocationAvailable?.equals(false)!!) {
                postValue(
                    LocationData(
                        false,
                        Location("")
                    )
                )
            }
        }
    }
}

class LocationData(
    var isPermissionAvailable : Boolean,
    var location : Location
)