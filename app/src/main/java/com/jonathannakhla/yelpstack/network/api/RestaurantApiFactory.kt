package com.jonathannakhla.yelpstack.network.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

class RestaurantApiFactory {
    companion object {
        private const val BASE_URL = "https://api.yelp.com/"
    }

    fun create(): RestaurantApi {
        val contentType = MediaType.get("application/json")
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(Json(JsonConfiguration(ignoreUnknownKeys = true)).asConverterFactory(contentType))
            .baseUrl(BASE_URL)
            .build()

        return RestaurantApiImpl(retrofit.create(RestaurantService::class.java))
    }
}