package com.jonathannakhla.yelpstack.repositories

import com.jonathannakhla.yelpstack.data.Resource
import com.jonathannakhla.yelpstack.data.Restaurant
import io.reactivex.rxjava3.core.Observable

interface RestaurantRepo {
    fun getRestaurants(latitude: Double, longitude: Double, offset: Int = 0): Observable<Resource<List<Restaurant>>>
}