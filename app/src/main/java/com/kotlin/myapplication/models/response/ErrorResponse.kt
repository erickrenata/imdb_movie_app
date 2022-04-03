package com.kotlin.myapplication.models.response


data class ErrorResponse(
    val status_code: Int?,
    val status_message: String?,
    val success: Boolean?
)