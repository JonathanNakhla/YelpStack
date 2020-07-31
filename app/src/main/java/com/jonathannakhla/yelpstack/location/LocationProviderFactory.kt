package com.jonathannakhla.yelpstack.location

import android.content.Context
import com.google.android.gms.location.LocationServices

class LocationProviderFactory {
    fun create(context: Context): LocationProvider {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context.applicationContext)
        return LocationProviderImpl(fusedLocationProviderClient)
    }
}