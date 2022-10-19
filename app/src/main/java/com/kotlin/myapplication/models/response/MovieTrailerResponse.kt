package com.kotlin.myapplication.models.response


/**
 * Created by @erickrenata on 19/10/22.
 */

data class MovieTrailerResponse(
    val page: Int?,
    val results: List<Trailer?>?,
    val total_pages: Int?,
    val total_results: Int?
)

data class Trailer(
    val id: String?,
    val iso_639_1: String?,
    val iso_3166_1: String?,
    val key: String?,
    val name: String?,
    val type: String?
)