package com.kotlin.myapplication.data

import androidx.annotation.Keep

@Keep
data class ErrorResponse(
    val status_code: Int?,
    val status_message: String?,
    val success: Boolean?
)