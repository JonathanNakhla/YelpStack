package com.jonathannakhla.yelpstack.network.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RestaurantsResult(@SerialName("businesses") val restaurants: List<RestaurantResult>)