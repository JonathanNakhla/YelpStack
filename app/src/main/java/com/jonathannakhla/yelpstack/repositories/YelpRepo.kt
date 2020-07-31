package com.jonathannakhla.yelpstack.repositories

import com.jonathannakhla.yelpstack.data.Resource
import com.jonathannakhla.yelpstack.data.Restaurant
import io.reactivex.rxjava3.core.Observable

interface YelpRepo {
    fun getRestaurants(latitude: Double, longitude: Double): Observable<Resource<List<Restaurant>>>
}