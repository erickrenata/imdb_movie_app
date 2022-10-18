package com.kotlin.myapplication.di

import com.kotlin.myapplication.di.viewmodel.MovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * Created by @erickrenata on 03/04/22.
 */


val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
}