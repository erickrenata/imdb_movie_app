package com.kotlin.myapplication.constants


/**
 * Created by @erickrenata on 03/04/22.
 */

class Constant {

    companion object {
        const val LANGUAGE_EN_US = "en-US"

        const val MOVIE_POPULAR = "popular"
        const val MOVIE_TOP_RATED = "top_rated"
        const val MOVIE_FAVORITES = "favorites"

        const val BASE_IMAGE_MOVIE = "https://image.tmdb.org/t/p/w185"
        const val BASE_IMAGE_THUMBNAIL_YOUTUBE = "https://img.youtube.com/vi/"
        const val BASE__YOUTUBE = "http://www.youtube.com/watch?v="
    }

}

enum class Status {
    SUCCESS,
    LOADING,
    ERROR
}

enum class ErrorCondition {
    AUTH,
    INTERNAL,
    BADREQUEST,
    NETWORKISSUE
}