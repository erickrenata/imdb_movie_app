package com.kotlin.myapplication.utils.ext

import com.kotlin.myapplication.models.response.ErrorResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import okhttp3.ResponseBody
import org.json.JSONObject


/**
 * Created by @erickrenata on 03/04/22.
 */

fun ResponseBody?.handleError(): String? {
    val errorJson = JSONObject(this?.string().filterEmpty()).toString()
    val moshi = Moshi.Builder().build()
    val adapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
    val message: ErrorResponse? = adapter.fromJson(errorJson)

    return message?.status_message
}