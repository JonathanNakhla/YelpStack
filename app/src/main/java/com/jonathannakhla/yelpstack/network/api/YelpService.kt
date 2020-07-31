package com.jonathannakhla.yelpstack.network.api

import com.jonathannakhla.yelpstack.network.data.RestaurantsResult
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface YelpService {
    @GET("v3/businesses/search")
    fun getRestaurants(@Header("Authorization") token: String,
                       @Query("location") location: String): Single<RestaurantsResult>
}