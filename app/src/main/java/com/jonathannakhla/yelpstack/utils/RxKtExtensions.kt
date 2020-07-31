package com.jonathannakhla.yelpstack.utils

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

fun Disposable.into(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}