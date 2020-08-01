package com.jonathannakhla.yelpstack.repositories

import com.jonathannakhla.yelpstack.data.Resource
import com.jonathannakhla.yelpstack.data.Restaurant
import com.jonathannakhla.yelpstack.network.api.RestaurantApi
import com.jonathannakhla.yelpstack.network.data.toRestaurant
import com.jonathannakhla.yelpstack.storage.RestaurantStorage
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

internal class RestaurantRepoImpl(private val restaurantApi: RestaurantApi,
                                  private val restaurantStorage: RestaurantStorage) : RestaurantRepo {
    private lateinit var repoDisposable: Disposable

    private val behaviorSubject by lazy {
        BehaviorSubject.create<Resource<List<Restaurant>>>()
    }

    override fun getRestaurants(latitude: Double, longitude: Double, offset: Int): Observable<Resource<List<Restaurant>>> {
        return behaviorSubject
            .doOnSubscribe { initializeRestaurantApi(latitude, longitude, offset) }
    }

    private fun initializeRestaurantApi(latitude: Double, longitude: Double, offset: Int) {
        if (this::repoDisposable.isInitialized) {
            repoDisposable.dispose()
        }

        repoDisposable = restaurantApi.getRestaurants(latitude, longitude, offset)
            .subscribeOn(Schedulers.io())
            .map { restaurantsResult ->
                restaurantsResult.restaurants.map { it.toRestaurant() }
            }
            .map<Resource<List<Restaurant>>> {
                restaurantStorage.saveRestaurants(it)
                Resource.Success(restaurantStorage.getAllRestaurants())
            }
            .onErrorReturn { Resource.Error(it.message ?: "", restaurantStorage.getAllRestaurants()) }
            .subscribe ({
                behaviorSubject.onNext(it)
            }, {
                behaviorSubject.onNext(Resource.Error(it.message ?: "", emptyList()))
            })

    }
}