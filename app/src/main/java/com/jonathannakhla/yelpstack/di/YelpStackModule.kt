package com.jonathannakhla.yelpstack.di

import com.jonathannakhla.yelpstack.location.LocationProviderFactory
import com.jonathannakhla.yelpstack.network.api.RestaurantApiFactory
import com.jonathannakhla.yelpstack.repositories.RestaurantRepoFactory
import com.jonathannakhla.yelpstack.storage.RestaurantStorageFactory
import com.jonathannakhla.yelpstack.viewmodels.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val yelpStackModule = module {
    factory { RestaurantApiFactory().create() }
    factory { RestaurantStorageFactory().create() }
    factory { RestaurantRepoFactory().create(get(), get()) }
    factory { LocationProviderFactory().create(androidContext()) }
}

val vmModule = module {
    viewModel { MainViewModel(get(), get()) }
}