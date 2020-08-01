package com.jonathannakhla.yelpstack.repositories

import com.jonathannakhla.yelpstack.network.api.RestaurantApi
import com.jonathannakhla.yelpstack.storage.RestaurantStorage

class RestaurantRepoFactory {

    fun create(restaurantApi: RestaurantApi, restaurantStorage: RestaurantStorage): RestaurantRepo {
        return RestaurantRepoImpl(restaurantApi, restaurantStorage)
    }
}