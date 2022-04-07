package com.kotlin.myapplication.di.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.myapplication.di.repository.MovieRepository
import com.kotlin.myapplication.models.item.MovieItemModel
import com.kotlin.myapplication.models.mapper.setFavoriteValue
import com.kotlin.myapplication.models.mapper.toMovieItem
import com.kotlin.myapplication.utils.ext.filterEmpty
import com.kotlin.myapplication.utils.ext.handleError
import com.kotlin.myapplication.utils.network.Resource
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent


/**
 * Created by @erickrenata on 03/04/22.
 */

class MovieViewModel(
    private val repository: MovieRepository
) : ViewModel(), KoinComponent {

    val popularMovieList = MutableLiveData<Resource<List<MovieItemModel>>>()
    val topRatedMovieList = MutableLiveData<Resource<List<MovieItemModel>>>()

    fun callGetPopularMovieList() = viewModelScope.launch {
        popularMovieList.postValue(Resource.loading(true))
        repository.getPopularMovieList(1).let {
            popularMovieList.postValue(Resource.loading(false))
            if (it.isSuccessful) {
                popularMovieList.postValue(Resource.success(it.body()?.toMovieItem().setFavoriteValue(getSavedMoviesSync())))
            } else {
                popularMovieList.postValue(
                    Resource.error(
                        it.errorBody().handleError().filterEmpty()
                    )
                )
            }
        }
    }

    fun callGetTopRatedMovieList() = viewModelScope.launch {
        topRatedMovieList.postValue(Resource.loading(true))
        repository.getTopRatedMovieList(1).let {
            topRatedMovieList.postValue(Resource.loading(false))
            if (it.isSuccessful) {
                topRatedMovieList.postValue(Resource.success(it.body()?.toMovieItem().setFavoriteValue(getSavedMoviesSync())))
            } else {
                topRatedMovieList.postValue(
                    Resource.error(
                        it.errorBody().handleError().filterEmpty()
                    )
                )
            }
        }
    }

    fun getFavoriteMovieByID(id: Int?, movies: List<MovieItemModel>): MovieItemModel?{
        return movies.find {
            id == it.id
        }
    }

    fun saveMovie(movie: MovieItemModel) = viewModelScope.launch {
        repository.upsert(movie)
    }

    fun getSavedMovies() = repository.getAllMovies()

    private suspend fun getSavedMoviesSync() = repository.getAllMoviesSync()

    fun deleteMovies(movie: MovieItemModel) = viewModelScope.launch {
        repository.deleteMovie(movie)
    }
}