package com.kotlin.myapplication.di.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.myapplication.di.repository.MovieRepository
import com.kotlin.myapplication.models.item.MovieItemModel
import com.kotlin.myapplication.models.mapper.setFavoriteValue
import com.kotlin.myapplication.models.mapper.toMovieItem
import com.kotlin.myapplication.models.response.Review
import com.kotlin.myapplication.models.response.Trailer
import com.kotlin.myapplication.utils.ext.getErrorMessage
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
    val trailerMovieList = MutableLiveData<Resource<List<Trailer?>>>()
    val reviewMovieList = MutableLiveData<Resource<List<Review?>>>()

    fun callGetPopularMovieList() = viewModelScope.launch {
        popularMovieList.postValue(Resource.loading(true))
        repository.getPopularMovieList(1).let {
            popularMovieList.postValue(Resource.loading(false))
            if (it.isSuccessful) {
                popularMovieList.postValue(
                    Resource.success(
                        it.body()?.toMovieItem().setFavoriteValue(getSavedMoviesSync())
                    )
                )
            } else {
                popularMovieList.postValue(
                    Resource.error(
                        it.errorBody().handleError().getErrorMessage()
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
                topRatedMovieList.postValue(
                    Resource.success(
                        it.body()?.toMovieItem().setFavoriteValue(getSavedMoviesSync())
                    )
                )
            } else {
                topRatedMovieList.postValue(
                    Resource.error(
                        it.errorBody().handleError().getErrorMessage()
                    )
                )
            }
        }
    }

    fun callGetTrailerMovieList(id: String) = viewModelScope.launch {
        trailerMovieList.postValue(Resource.loading(true))
        repository.getTrailerMovieList(id).let {
            trailerMovieList.postValue(Resource.loading(false))
            if (it.isSuccessful) {
                trailerMovieList.postValue(Resource.success(it.body()?.results))
            } else {
                trailerMovieList.postValue(
                    Resource.error(
                        it.errorBody().handleError().getErrorMessage()
                    )
                )
            }
        }
    }

    fun callGetReviewMovieList(id: String) = viewModelScope.launch {
        reviewMovieList.postValue(Resource.loading(true))
        repository.getReviewMovieList(id).let {
            reviewMovieList.postValue(Resource.loading(false))
            if (it.isSuccessful) {
                reviewMovieList.postValue(Resource.success(it.body()?.results))
            } else {
                reviewMovieList.postValue(
                    Resource.error(
                        it.errorBody().handleError().getErrorMessage()
                    )
                )
            }
        }
    }

    fun getFavoriteMovieByID(id: Int?, movies: List<MovieItemModel>): MovieItemModel? {
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