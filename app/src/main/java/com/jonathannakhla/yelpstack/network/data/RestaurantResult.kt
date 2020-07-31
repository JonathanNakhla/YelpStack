package com.jonathannakhla.yelpstack.network.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RestaurantResult(
    @SerialName("name") val name: String,
    @SerialName("image_url") val imageUrl: String,
    @SerialName("rating") val rating: Double
)