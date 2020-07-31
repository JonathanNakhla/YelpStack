package com.jonathannakhla.yelpstack.repositories

import com.jonathannakhla.yelpstack.network.api.YelpApi

class YelpRepoFactory {
    companion object {
        @Volatile private var instance: YelpRepo? = null
    }

    fun getInstance(yelpApi: YelpApi): YelpRepo {
        return instance?: synchronized(this) {
            instance ?: YelpRepoImpl(yelpApi)
                .also { instance = it }
        }
    }
}