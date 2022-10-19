package com.kotlin.myapplication.di.repository

import com.kotlin.myapplication.api.ApiHelper
import com.kotlin.myapplication.db.MovieDao
import com.kotlin.myapplication.models.item.MovieItemModel


/**
 * Created by @erickrenata on 03/04/22.
 */

class MovieRepository(private val apiHelper: ApiHelper, private val dao: MovieDao) {

    suspend fun getPopularMovieList(page: Int) = apiHelper.getPopularMovieList(page)

    suspend fun getTopRatedMovieList(page: Int) = apiHelper.getTopRatedMovieList(page)

    suspend fun upsert(movie: MovieItemModel) = dao.upsert(movie)

    fun getAllMovies() = dao.getAllMovies()

    suspend fun getAllMoviesSync() = dao.getAllMoviesSync()

    suspend fun deleteMovie(movie: MovieItemModel) = dao.deleteMovie(movie)

    suspend fun getTrailerMovieList(id: String) = apiHelper.getTrailerMovieList(id)

    suspend fun getReviewMovieList(id: String) = apiHelper.getReviewMovieList(id)

}