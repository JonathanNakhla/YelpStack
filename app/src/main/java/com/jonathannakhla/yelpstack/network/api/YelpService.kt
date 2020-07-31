package com.jonathannakhla.yelpstack.network.api

import com.jonathannakhla.yelpstack.network.data.RestaurantsResult
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface YelpService {
    @GET("v3/businesses/search")
    fun getRestaurants(): Single<RestaurantsResult>
}