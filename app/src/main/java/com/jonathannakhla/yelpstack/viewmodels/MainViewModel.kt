package com.jonathannakhla.yelpstack.viewmodels

import androidx.lifecycle.ViewModel
import com.jonathannakhla.yelpstack.data.Resource
import com.jonathannakhla.yelpstack.data.Restaurant
import com.jonathannakhla.yelpstack.location.LocationProvider
import com.jonathannakhla.yelpstack.repositories.RestaurantRepo
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

class MainViewModel(private val restaurantRepo: RestaurantRepo,
                    private val locationProvider: LocationProvider) : ViewModel() {

    companion object {
        private const val OFFSET = 20
    }

    private lateinit var restaurantRepoDisposable: Disposable

    private val behaviorSubject by lazy {
        BehaviorSubject.create<Resource<List<Restaurant>>>()
    }

    private var currentOffset = 0

    fun getRestaurants(): Observable<Resource<List<Restaurant>>> {
        return behaviorSubject
            .doOnSubscribe { initializeRestaurantRepo() }
    }

    fun getMoreRestaurants() {
        currentOffset += OFFSET
        initializeRestaurantRepo(currentOffset)
    }

    private fun initializeRestaurantRepo(offset: Int = 0) {

        if (this::restaurantRepoDisposable.isInitialized) {
            restaurantRepoDisposable.dispose()
        }

        restaurantRepoDisposable = locationProvider.getCurrentLocation()
            .flatMapObservable { restaurantRepo.getRestaurants(it.latitude, it.longitude, offset) }
            .subscribeOn(Schedulers.io())
            .subscribe (
                {
                    behaviorSubject.onNext(it)
                },
                {
                    behaviorSubject.onNext(Resource.Error(it.message ?: "Encountered error", emptyList()))
                }
            )
    }

    override fun onCleared() {
        super.onCleared()
        if (this::restaurantRepoDisposable.isInitialized) {
            restaurantRepoDisposable.dispose()
        }
    }
}