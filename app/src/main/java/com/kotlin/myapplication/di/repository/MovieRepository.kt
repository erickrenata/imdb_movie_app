package com.kotlin.myapplication.di.repository

import com.kotlin.myapplication.api.ApiHelper


/**
 * Created by @erickrenata on 03/04/22.
 */

class MovieRepository(private val apiHelper: ApiHelper) {

    suspend fun getPopularMovieList(page: Int) = apiHelper.getPopularMovieList(page)

    suspend fun getTopRatedMovieList(page: Int) = apiHelper.getTopRatedMovieList(page)

}