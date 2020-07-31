package com.jonathannakhla.yelpstack.di

import com.jonathannakhla.yelpstack.location.LocationProviderFactory
import com.jonathannakhla.yelpstack.location.LocationProviderImpl
import com.jonathannakhla.yelpstack.network.api.YelpApiFactory
import com.jonathannakhla.yelpstack.repositories.YelpRepoFactory
import com.jonathannakhla.yelpstack.viewmodels.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val yelpStackModule = module {
    factory { YelpApiFactory().create() }
    factory { YelpRepoFactory().getInstance(get()) }
    factory { LocationProviderFactory().create(androidContext()) }
}

val vmModule = module {
    viewModel { MainViewModel(get(), get()) }
}