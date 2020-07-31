package com.jonathannakhla.yelpstack.repositories

import com.jonathannakhla.yelpstack.data.Resource
import com.jonathannakhla.yelpstack.data.Restaurant
import io.reactivex.rxjava3.core.Observable

interface YelpRepo {
    fun getRestaurants(): Observable<Resource<List<Restaurant>>>
}