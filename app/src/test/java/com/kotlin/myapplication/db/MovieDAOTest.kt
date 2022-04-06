package com.kotlin.myapplication.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.kotlin.myapplication.getOrAwaitValue
import com.kotlin.myapplication.models.item.MovieItemModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by @erickrenata on 06/04/22.
 */

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class MovieDAOTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: MovieDatabase
    private lateinit var dao: MovieDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MovieDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.getMovieDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun upsert() = runBlockingTest {
        val movie = MovieItemModel(
            id = 1,
            "title",
            "originalTitle",
            "date",
            "backdropImg",
            "posterImg",
            "description",
            0.0,
            0,
            "language",
            false
        )
        dao.upsert(movie)

        val allMoviesItem = dao.getAllMovies().getOrAwaitValue()

        assertThat(allMoviesItem).contains(movie)
    }

    @Test
    fun deleteMovie() = runBlockingTest {
        val movie = MovieItemModel(
            id = 1,
            "title",
            "originalTitle",
            "date",
            "backdropImg",
            "posterImg",
            "description",
            0.0,
            0,
            "language",
            false
        )
        dao.upsert(movie)
        dao.deleteMovie(movie)

        val allMoviesItem = dao.getAllMovies().getOrAwaitValue()

        assertThat(allMoviesItem).doesNotContain(movie)
    }
}