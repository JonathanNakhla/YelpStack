package com.jonathannakhla.yelpstack.storage

import com.jonathannakhla.yelpstack.data.Restaurant

interface RestaurantStorage {
    fun saveRestaurants(restaurants: List<Restaurant>)
    fun getAllRestaurants(): List<Restaurant>
    fun clearAllRestaurants()
}