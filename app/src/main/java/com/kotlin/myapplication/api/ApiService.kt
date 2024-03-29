package com.kotlin.myapplication.api

import com.kotlin.myapplication.BuildConfig
import com.kotlin.myapplication.constants.Constant.Companion.LANGUAGE_EN_US
import com.kotlin.myapplication.models.response.MovieResponse
import com.kotlin.myapplication.models.response.MovieReviewResponse
import com.kotlin.myapplication.models.response.MovieTrailerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by @erickrenata on 03/04/22.
 */

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovieList(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE_EN_US
    ): Response<MovieResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovieList(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE_EN_US
    ): Response<MovieResponse>

    @GET("movie/{id}/videos")
    suspend fun getTrailerMovieList(
        @Path("id") page: String,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE_EN_US
    ): Response<MovieTrailerResponse>

    @GET("movie/{id}/reviews")
    suspend fun getReviewMovieList(
        @Path("id") page: String,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE_EN_US
    ): Response<MovieReviewResponse>
}