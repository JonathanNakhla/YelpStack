package com.jonathannakhla.yelpstack.network.api

import com.jonathannakhla.yelpstack.network.data.RestaurantsResult
import io.reactivex.rxjava3.core.Single

interface RestaurantApi {
    fun getRestaurants(latitude: Double, longitude: Double, offset: Int): Single<RestaurantsResult>
}