package com.kotlin.myapplication.utils.ext

import com.kotlin.myapplication.constants.ErrorCondition
import com.kotlin.myapplication.models.response.ErrorResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import okhttp3.ResponseBody
import org.json.JSONObject


/**
 * Created by @erickrenata on 03/04/22.
 */

fun ResponseBody?.handleError(): ErrorCondition {
    val errorJson = JSONObject(this?.string().filterEmpty()).toString()
    val moshi = Moshi.Builder().build()
    val adapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
    val message: ErrorResponse? = adapter.fromJson(errorJson)

    return when (message?.status_code) {
        400 -> ErrorCondition.BADREQUEST
        401 -> ErrorCondition.AUTH
        403 -> ErrorCondition.INTERNAL
        500 -> ErrorCondition.NETWORKISSUE
        else -> ErrorCondition.NETWORKISSUE
    }
}

fun ErrorCondition.getErrorMessage() : String{
    return when (this) {
        ErrorCondition.BADREQUEST -> "BadRequest Error"
        ErrorCondition.AUTH -> "Auth Error"
        ErrorCondition.INTERNAL -> "Internal Error"
        ErrorCondition.NETWORKISSUE -> "Network Issue"
    }
}