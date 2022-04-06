package com.kotlin.myapplication.di

import com.kotlin.myapplication.di.repository.MovieRepository
import org.koin.dsl.module


/**
 * Created by @erickrenata on 03/04/22.
 */


val repoModule = module {
    single { MovieRepository(get(), get()) }
}