package com.kotlin.myapplication.api

import com.kotlin.myapplication.models.response.MovieResponse
import retrofit2.Response


/**
 * Created by @erickrenata on 03/04/22.
 */


interface ApiHelper {

    suspend fun getPopularMovieList(page: Int): Response<MovieResponse>

    suspend fun getTopRatedMovieList(page: Int): Response<MovieResponse>
}