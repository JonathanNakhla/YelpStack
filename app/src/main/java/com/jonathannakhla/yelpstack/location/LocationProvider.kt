package com.jonathannakhla.yelpstack.location

import io.reactivex.rxjava3.core.Single

interface LocationProvider {
    fun getCurrentLocation(): Single<Location>
}