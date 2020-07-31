package com.jonathannakhla.yelpstack.network.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

class YelpApiFactory {
    companion object {
        private const val BASE_URL = "https://api.yelp.com/"
    }

    fun create(): YelpApi {
        val contentType = MediaType.get("application/json")
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(Json.Default.asConverterFactory(contentType))
            .baseUrl(BASE_URL)
            .build()

        return YelpApiImpl(retrofit.create(YelpService::class.java))
    }
}