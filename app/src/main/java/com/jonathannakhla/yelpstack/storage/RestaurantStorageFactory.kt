package com.jonathannakhla.yelpstack.storage

class RestaurantStorageFactory {
    fun create(): RestaurantStorage = RestaurantStorageMemoryImpl()
}