package com.kotlin.myapplication.di.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.myapplication.data.MovieResponse
import com.kotlin.myapplication.di.repository.MovieRepository
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

    val loader = MutableLiveData<Boolean>()
    val movieList = MutableLiveData<Resource<MovieResponse>>()

    fun callGetPopularMovieList() {
        viewModelScope.launch {
            loader.postValue(true)
            repository.getPopularMovieList(1).let {
                loader.postValue(false)
                if (it.isSuccessful) {
                    movieList.postValue(Resource.success(it.body()))
                } else {
                    movieList.postValue(
                        Resource.error(
                            it.errorBody().handleError().filterEmpty()
                        )
                    )
                }
            }
        }
    }
}