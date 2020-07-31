package com.jonathannakhla.yelpstack.di

import com.jonathannakhla.yelpstack.network.api.YelpApiFactory
import com.jonathannakhla.yelpstack.viewmodels.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val yelpStackModule = module {
    factory { YelpApiFactory().create() }
}

val vmModule = module {
    viewModel { MainViewModel() }
}