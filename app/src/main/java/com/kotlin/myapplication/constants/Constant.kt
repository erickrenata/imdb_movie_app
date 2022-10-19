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