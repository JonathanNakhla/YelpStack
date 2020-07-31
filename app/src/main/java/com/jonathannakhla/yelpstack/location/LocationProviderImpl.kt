package com.jonathannakhla.yelpstack.location

import com.google.android.gms.location.FusedLocationProviderClient
import io.reactivex.rxjava3.core.Single

class LocationProviderImpl(private val fusedLocationProviderClient: FusedLocationProviderClient): LocationProvider {

    override fun getCurrentLocation(): Single<Location> {
        return Single.create { emitter ->
            // TODO handle permissions not given
            fusedLocationProviderClient.lastLocation.addOnSuccessListener { googleLocation ->
                emitter.onSuccess(Location(googleLocation.latitude, googleLocation.longitude))
            }
        }
    }
}