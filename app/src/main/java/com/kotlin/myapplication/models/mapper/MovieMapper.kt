package com.kotlin.myapplication.models.mapper

import com.kotlin.myapplication.models.item.MovieItemViewModel
import com.kotlin.myapplication.models.response.MovieResponse


/**
 * Created by @erickrenata on 04/04/22.
 */


fun MovieResponse?.toMovieItem(): List<MovieItemViewModel>? {
    return if (this?.results.isNullOrEmpty()) {
        arrayListOf()
    } else {
        this?.results?.map {
            MovieItemViewModel(
                title = it?.title,
                date = it?.release_date,
                urlImage = it?.poster_path,
                description = it?.overview
            )
        }
    }
}