package com.jonathannakhla.yelpstack.network.api

import com.jonathannakhla.yelpstack.network.data.RestaurantsResult
import io.reactivex.rxjava3.core.Single

class YelpApiImpl(private val yelpService: YelpService) : YelpApi {
    companion object {
        private const val TOKEN = "Bearer itoMaM6DJBtqD54BHSZQY9WdWR5xI_CnpZdxa3SG5i7N0M37VK1HklDDF4ifYh8SI-P2kI_mRj5KRSF4_FhTUAkEw322L8L8RY6bF1UB8jFx3TOR0-wW6Tk0KftNXXYx"
    }
    override fun getRestaurants(latitude: Double, longitude: Double): Single<RestaurantsResult> {
        return yelpService.getRestaurants(TOKEN, latitude, longitude)
    }
}