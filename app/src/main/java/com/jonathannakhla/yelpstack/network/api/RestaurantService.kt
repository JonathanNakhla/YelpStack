package com.jonathannakhla.yelpstack.network.api

import com.jonathannakhla.yelpstack.network.data.RestaurantsResult
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RestaurantService {
    @GET("v3/businesses/search")
    fun getRestaurants(@Header("Authorization") token: String,
                       @Query("latitude") latitude: Double,
                       @Query("longitude") longitude: Double,
                       @Query("offset") offset: Int = 0): Single<RestaurantsResult>
}