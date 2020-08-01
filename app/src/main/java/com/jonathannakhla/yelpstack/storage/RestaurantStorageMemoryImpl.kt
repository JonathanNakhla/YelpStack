package com.jonathannakhla.yelpstack.storage

import com.jonathannakhla.yelpstack.data.Restaurant

internal class RestaurantStorageMemoryImpl : RestaurantStorage {

    private var restaurantsInMemory: MutableList<Restaurant> = mutableListOf()

    override fun saveRestaurants(restaurants: List<Restaurant>) {
        restaurantsInMemory.addAll(restaurants)
    }

    override fun getAllRestaurants(): List<Restaurant> {
        return restaurantsInMemory
    }

    override fun clearAllRestaurants() {
        restaurantsInMemory.clear()
    }
}