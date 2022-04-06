package com.kotlin.myapplication.models.mapper

import com.kotlin.myapplication.models.item.MovieItemModel
import com.kotlin.myapplication.models.response.MovieResponse
import com.kotlin.myapplication.utils.DateUtils
import com.kotlin.myapplication.utils.DateUtils.MMMM_DD_YYYY
import com.kotlin.myapplication.utils.DateUtils.YYYY_MM_DD
import com.kotlin.myapplication.utils.ext.filterEmpty


/**
 * Created by @erickrenata on 04/04/22.
 */


fun MovieResponse?.toMovieItem(): List<MovieItemModel>? {
    return if (this?.results.isNullOrEmpty()) {
        arrayListOf()
    } else {
        this?.results?.map {
            MovieItemModel(
                id = it?.id,
                title = it?.title,
                originalTitle = it?.original_title,
                date = DateUtils.formatDate(
                    date = it?.release_date,
                    oldFormat = YYYY_MM_DD,
                    newFormat = MMMM_DD_YYYY
                ),
                posterImage = it?.poster_path,
                backdropImage = it?.backdrop_path,
                description = it?.overview,
                voteAverage = it?.vote_average,
                voteCount = it?.vote_count,
                language = it?.original_language
            )
        }
    }
}

fun List<MovieItemModel>?.setFavoriteValue(savedList: List<MovieItemModel>?): List<MovieItemModel>? {
    return if (this?.isNullOrEmpty() == true) {
        arrayListOf()
    } else {
        this.map {
            val favouritedMovie = savedList?.find { saved ->
                saved.id == it.id
            }
            it.isLiked = favouritedMovie != null
        }
        return this
    }
}

fun List<MovieItemModel>?.setAllMoviesToFavorites(): List<MovieItemModel> {
    return if (this?.isNullOrEmpty() == true) {
        arrayListOf()
    } else {
        this.map {
            it.isLiked = true
        }
        return this
    }
}