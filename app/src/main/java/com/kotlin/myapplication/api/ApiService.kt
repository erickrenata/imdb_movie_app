package com.kotlin.myapplication.api

import com.kotlin.myapplication.BuildConfig
import com.kotlin.myapplication.constants.Constant.Companion.LANGUAGE_EN_US
import com.kotlin.myapplication.models.body.LoginRequest
import com.kotlin.myapplication.models.response.LoginResponse
import com.kotlin.myapplication.models.response.MovieResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
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

    @POST("https://6124ba6d300c540017873d1e.mockapi.io/api/v1/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}