package com.kotlin.myapplication.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kotlin.myapplication.models.item.MovieItemModel


/**
 * Created by @erickrenata on 03/04/22.
 */

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(movie: MovieItemModel): Long

    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<MovieItemModel>>

    @Query("SELECT * FROM movies")
    suspend fun getAllMoviesSync(): List<MovieItemModel>

    @Delete
    suspend fun deleteMovie(movie: MovieItemModel)
}