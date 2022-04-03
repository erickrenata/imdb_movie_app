package com.kotlin.myapplication.model


data class ErrorResponse(
    val status_code: Int?,
    val status_message: String?,
    val success: Boolean?
)