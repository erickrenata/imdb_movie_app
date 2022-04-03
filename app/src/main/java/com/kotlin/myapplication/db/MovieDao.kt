package com.kotlin.myapplication.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kotlin.myapplication.model.Movie


/**
 * Created by @erickrenata on 03/04/22.
 */

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(movie: Movie): Long

    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<Movie>>

    @Delete
    suspend fun deleteMovie(movie: Movie)
}