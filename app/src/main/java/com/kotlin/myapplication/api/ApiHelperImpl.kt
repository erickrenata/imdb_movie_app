package com.kotlin.myapplication.api

import com.kotlin.myapplication.model.MovieResponse
import retrofit2.Response


/**
 * Created by @erickrenata on 03/04/22.
 */

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getPopularMovieList(page: Int): Response<MovieResponse> =
        apiService.getPopularMovieList(page)

    override suspend fun getTopRatedMovieList(page: Int): Response<MovieResponse> =
        apiService.getTopRatedMovieList(page)

}