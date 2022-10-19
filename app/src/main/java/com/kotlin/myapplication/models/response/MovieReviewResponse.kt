package com.kotlin.myapplication.models.response


/**
 * Created by @erickrenata on 19/10/22.
 */

data class MovieReviewResponse(
    val page: Int?,
    val results: List<Review?>?,
    val total_pages: Int?,
    val total_results: Int?
)

data class Review(
    val id: String?,
    val author: String?,
    val content: String?,
    val url: String?
)