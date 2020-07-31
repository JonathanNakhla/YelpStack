package com.jonathannakhla.yelpstack.network.api

import com.jonathannakhla.yelpstack.network.data.RestaurantsResult
import io.reactivex.rxjava3.core.Single

class YelpApiImpl(private val yelpService: YelpService) : YelpApi {
    override fun getRestaurants(): Single<RestaurantsResult> {
        return yelpService.getRestaurants()
    }
}