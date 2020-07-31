package com.jonathannakhla.yelpstack.repositories

import com.jonathannakhla.yelpstack.data.Resource
import com.jonathannakhla.yelpstack.data.Restaurant
import com.jonathannakhla.yelpstack.network.api.YelpApi
import com.jonathannakhla.yelpstack.network.data.toRestaurant
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

class YelpRepoImpl(private val yelpApi: YelpApi) : YelpRepo {
    private lateinit var repoDisposable: Disposable

    private val behaviorSubject by lazy {
        BehaviorSubject.create<Resource<List<Restaurant>>>()
    }

    override fun getRestaurants(): Observable<Resource<List<Restaurant>>> {
        return behaviorSubject
            .doOnSubscribe { initializeRestaurantApi() }
    }

    private fun initializeRestaurantApi() {
        if (this::repoDisposable.isInitialized) {
            repoDisposable.dispose()
        }

        repoDisposable = yelpApi.getRestaurants()
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { behaviorSubject.onNext(Resource.Loading(emptyList())) } // TODO might remove
            .map { restaurantsResult ->
                restaurantsResult.restaurants.map { it.toRestaurant() }
            }
            .map<Resource<List<Restaurant>>> { Resource.Success(it) }
            .onErrorReturn { Resource.Error(it.message ?: "", emptyList()) }
            .subscribe ({
                behaviorSubject.onNext(it)
            }, {
                behaviorSubject.onNext(Resource.Error(it.message ?: "", emptyList()))
            })

    }
}