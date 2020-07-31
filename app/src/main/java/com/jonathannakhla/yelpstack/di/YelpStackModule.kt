package com.jonathannakhla.yelpstack.di

import com.jonathannakhla.yelpstack.viewmodels.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val yelpStackModule = module {

}

val vmModule = module {
    viewModel { MainViewModel() }
}