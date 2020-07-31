package com.jonathannakhla.yelpstack.application

import android.app.Application
import com.jonathannakhla.yelpstack.di.vmModule
import com.jonathannakhla.yelpstack.di.yelpStackModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class YelpStackApplication: Application() {
    init {
        startKoin {
            androidContext(this@YelpStackApplication)
            modules(listOf(yelpStackModule, vmModule))
        }
    }
}