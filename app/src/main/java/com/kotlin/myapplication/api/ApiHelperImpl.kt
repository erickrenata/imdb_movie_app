package com.kotlin.myapplication.api

import com.kotlin.myapplication.models.response.MovieResponse
import com.kotlin.myapplication.models.response.MovieReviewResponse
import com.kotlin.myapplication.models.response.MovieTrailerResponse
import retrofit2.Response


/**
 * Created by @erickrenata on 03/04/22.
 */

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getPopularMovieList(page: Int): Response<MovieResponse> =
        apiService.getPopularMovieList(page)

    override suspend fun getTopRatedMovieList(page: Int): Response<MovieResponse> =
        apiService.getTopRatedMovieList(page)

    override suspend fun getTrailerMovieList(id: String): Response<MovieTrailerResponse> =
        apiService.getTrailerMovieList(id)

    override suspend fun getReviewMovieList(id: String): Response<MovieReviewResponse> =
        apiService.getReviewMovieList(id)

}