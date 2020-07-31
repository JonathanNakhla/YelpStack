package com.jonathannakhla.yelpstack.viewmodels

import androidx.lifecycle.ViewModel
import com.jonathannakhla.yelpstack.data.Resource
import com.jonathannakhla.yelpstack.data.Restaurant
import com.jonathannakhla.yelpstack.repositories.YelpRepo
import com.jonathannakhla.yelpstack.utils.into
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

class MainViewModel(private val yelpRepo: YelpRepo) : ViewModel() {

    private lateinit var restaurantRepoDisposable: Disposable

    private val behaviorSubject by lazy {
        BehaviorSubject.create<Resource<List<Restaurant>>>()
    }

    fun getRestaurants(): Observable<Resource<List<Restaurant>>> {
        return behaviorSubject
            .doOnSubscribe { initializeRestaurantRepo() }
    }

    private fun initializeRestaurantRepo() {
        if (!this::restaurantRepoDisposable.isInitialized) {
            restaurantRepoDisposable = yelpRepo.getRestaurants()
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
    }

    override fun onCleared() {
        super.onCleared()
        if (this::restaurantRepoDisposable.isInitialized) {
            restaurantRepoDisposable.dispose()
        }
    }
}