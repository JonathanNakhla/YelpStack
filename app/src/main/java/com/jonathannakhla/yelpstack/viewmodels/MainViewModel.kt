package com.jonathannakhla.yelpstack.viewmodels

import androidx.lifecycle.ViewModel
import com.jonathannakhla.yelpstack.data.Restaurant
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class MainViewModel : ViewModel() {

    private val behaviorSubject by lazy {
        BehaviorSubject.create<List<Restaurant>>()
    }

    companion object {
        private val restaurants = listOf(
            Restaurant(
                name = "World Famous Hotboys",
                imageUrl = "https://s3-media0.fl.yelpcdn.com/bphoto/UoRDxx_sHkWSv9zUdQ8tmw/o.jpg",
                rating = 4.5
            ), Restaurant(
                name = "Homeroom",
                imageUrl = "https://s3-media0.fl.yelpcdn.com/bphoto/pv8zKywavAwUoCxl0xNPLg/o.jpg",
                rating = 4.0
            ), Restaurant(
                name = "Monster Pho",
                imageUrl = "https://s3-media0.fl.yelpcdn.com/bphoto/ouEp1nb6wV1e5KV5AvV3vQ/o.jpg",
                rating = 4.0
            )
        )
    }

    fun getRestaurants(): Observable<List<Restaurant>> {
        behaviorSubject.onNext(restaurants)
        return behaviorSubject
    }

    override fun onCleared() {
        super.onCleared()
        // TODO clear future CompositeDisposable
    }
}