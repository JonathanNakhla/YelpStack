package com.jonathannakhla.yelpstack.network.data

import com.jonathannakhla.yelpstack.data.Restaurant

fun RestaurantResult.toRestaurant(): Restaurant {
    return Restaurant(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl,
        rating = this.rating
    )
}